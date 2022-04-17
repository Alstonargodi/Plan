package com.example.wetterbericht.view.todo

import android.app.Dialog
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.example.wetterbericht.databinding.FragmentDetailTodoListDialogBinding
import com.example.wetterbericht.viewmodel.LocalViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog


class DetailTodoDialog : BottomSheetDialogFragment() {

    private var _binding: FragmentDetailTodoListDialogBinding? = null
    private val binding get() = _binding!!

    lateinit var roomvmodel : LocalViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDetailTodoListDialogBinding.inflate(inflater, container, false)


        val deadlinedate = arguments?.getString("date")
        val deadlinetime = arguments?.getString("time")
        val title = arguments?.getString("title")
        val desc = arguments?.getString("desc")


        roomvmodel = ViewModelProvider(this).get(LocalViewModel::class.java)

        binding.tvbottomTodoDate.setText(deadlinedate)
        binding.tvbottomTodoTime.setText(deadlinetime)
        binding.tvbottomTodoTitle.setText(title)
        binding.tvbottomTodoDesc.setText(desc)



        return binding.root
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