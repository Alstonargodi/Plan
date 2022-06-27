package com.example.wetterbericht.view.fragment.home

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wetterbericht.databinding.FragmentDetailTodoListDialogBinding
import com.example.wetterbericht.model.local.TodoLocal
import com.example.wetterbericht.view.fragment.home.HomeFragment.Companion.homepage_key
import com.example.wetterbericht.view.fragment.home.adapter.SubTaskAdapter
import com.example.wetterbericht.viewmodel.local.LocalViewModel
import com.example.wetterbericht.viewmodel.utils.ViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.lang.Exception
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class DetailTodoDialog : BottomSheetDialogFragment() {
    private var _binding: FragmentDetailTodoListDialogBinding? = null
    private val binding get() = _binding!!


    private val timeFormat = SimpleDateFormat("hh:mm", Locale.getDefault())

    private val roomViewModel : LocalViewModel by viewModels{ ViewModelFactory.getInstance(requireContext())}


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDetailTodoListDialogBinding.inflate(inflater, container, false)

        val detailTodo = requireArguments().getParcelable<TodoLocal>(homepage_key)

        try {
            binding.apply {
                if (detailTodo != null) {
                    showDetailTask(detailTodo)
                    showSubtask(detailTodo.title)
                }
            }
        }catch (e : Exception){
            Log.d("detailActivity",e.message.toString())
        }


        return binding.root
    }

    private fun showDetailTask(detailTodo : TodoLocal){
        binding.tvbottomTodoTitle.setTextColor(detailTodo.levelColor)
        val result = "${detailTodo.startTime}-${detailTodo.endTime}"
        binding.tvbottomTodoTime.text = result
        binding.tvbottomTodoTitle.text = detailTodo.title
        binding.tvbottomTodoDesc.text = detailTodo.description
    }


    private fun showSubtask(title : String){
        val adapter = SubTaskAdapter()
        val recView = binding.rvSubtask
        recView.adapter = adapter
        recView.layoutManager = LinearLayoutManager(requireActivity())

        roomViewModel.readTodoSubtask(title).observe(viewLifecycleOwner){
            it.forEach {
                adapter.submitList(it.subtask)
            }
        }
    }


    //full dialog config
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.setOnShowListener {

            val bottomSheetDialog = it as BottomSheetDialog
            val parentLayout =
                bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            parentLayout?.let { it ->
                val behaviour = BottomSheetBehavior.from(it)
                setupFullHeight(it)
                behaviour.state = BottomSheetBehavior.STATE_HALF_EXPANDED
            }

        }
        return dialog
    }
    private fun setupFullHeight(bottomSheet: View) {
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        bottomSheet.layoutParams = layoutParams
    }

}