package com.example.wetterbericht.view.home

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wetterbericht.databinding.FragmentDetailTodoListDialogBinding
import com.example.wetterbericht.model.local.TodoLocal
import com.example.wetterbericht.view.home.HomeFragment.Companion.homepage_key
import com.example.wetterbericht.view.home.adapter.SubTaskAdapter
import com.example.wetterbericht.viewmodel.local.LocalViewModel
import com.example.wetterbericht.viewmodel.utils.obtainViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.lang.Exception

class DetailTodoDialog : BottomSheetDialogFragment() {
    private var _binding: FragmentDetailTodoListDialogBinding? = null
    private val binding get() = _binding!!

    private lateinit var roomVModel : LocalViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDetailTodoListDialogBinding.inflate(inflater, container, false)
        roomVModel = obtainViewModel(requireActivity())

        val detailTodo = arguments?.getParcelable<TodoLocal>(homepage_key)


        try {
            binding.apply {
                if (detailTodo != null) {
                    tvdetailTag.text = detailTodo.levelName
                    tvdetailTag.setBackgroundColor(detailTodo.levelColor)
                    tvdetailTag.setTextColor(Color.WHITE)

                    tvbottomTodoTime.text = detailTodo.timeDeadline
                    tvbottomTodoTitle.text = detailTodo.title
                    tvbottomTodoDesc.text = detailTodo.description
                    readSubtask(detailTodo.title)
                }
            }
        }catch (e : Exception){
            Log.d("detailActivity",e.message.toString())
        }


        return binding.root
    }


    private fun readSubtask(title : String){
        val adapter = SubTaskAdapter()
        val recView = binding.rvSubtask
        recView.adapter = adapter
        recView.layoutManager = LinearLayoutManager(requireActivity())

        roomVModel.readTodoandSubtask(title)
        roomVModel.responseTodoandSubtask.observe(viewLifecycleOwner){
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