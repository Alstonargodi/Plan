package com.example.wetterbericht.view.fragment.home.detail

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wetterbericht.databinding.FragmentDetailTodoListDialogBinding
import com.example.wetterbericht.model.local.TodoLocal
import com.example.wetterbericht.view.fragment.home.HomeFragment.Companion.homepage_key
import com.example.wetterbericht.view.fragment.home.HomeFragmentDirections
import com.example.wetterbericht.view.fragment.home.adapter.SubtaskRecyclerViewAdapter
import com.example.wetterbericht.viewmodel.local.LocalViewModel
import com.example.wetterbericht.viewmodel.utils.ViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*
import kotlin.math.abs

class DetailTodoDialog : BottomSheetDialogFragment() {
    private var _binding: FragmentDetailTodoListDialogBinding? = null
    private val binding get() = _binding!!

    private lateinit var detailTodo : TodoLocal
    private var durationTime = 0
    private val roomViewModel : LocalViewModel by viewModels{ ViewModelFactory.getInstance(requireContext())}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDetailTodoListDialogBinding.inflate(inflater, container, false)
        detailTodo = requireArguments().getParcelable(homepage_key)!!

        durationTime = countDownDuration()

        countDownDuration()

        try {
            binding.apply {
                showDetailTask(detailTodo)
                showSubtask(detailTodo.title)
            }
        }catch (e : Exception){
            Log.d("detailActivity",e.message.toString())
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnHabitsTodoDialog.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionFragmentHomeToCountdownFragment(
                    durationTime,detailTodo.title
                )
            )
        }
    }

    private fun showDetailTask(detailTodo : TodoLocal){
        binding.tvbottomTodoTitle.setTextColor(detailTodo.levelColor)
        val result = "${detailTodo.startTime}-${detailTodo.endTime}"
        binding.tvbottomTodoTime.text = result
        binding.tvbottomTodoTitle.text = detailTodo.title
        binding.tvbottomTodoDesc.text = detailTodo.description
        binding.tvbottomTodoDate.text = detailTodo.dateStart

    }

    private fun showSubtask(title : String){
        val adapter = SubtaskRecyclerViewAdapter()
        val recView = binding.rvSubtask
        recView.adapter = adapter
        recView.layoutManager = LinearLayoutManager(requireActivity())
        roomViewModel.readTodoSubtask(title).observe(viewLifecycleOwner){
            it.forEach {
                adapter.submitList(it.subtask)
            }
        }
    }

    private fun countDownDuration(): Int{
        val timeOne = LocalTime.parse(detailTodo.startTime)
        val timeTwo = LocalTime.parse(detailTodo.endTime)
        val result = timeOne.hour - timeTwo.hour
        return abs(result)
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