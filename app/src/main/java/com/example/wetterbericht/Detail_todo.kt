package com.example.wetterbericht

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.wetterbericht.databinding.FragmentDetailTodoListDialogBinding


class Detail_todo : BottomSheetDialogFragment() {

    private var _binding: FragmentDetailTodoListDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDetailTodoListDialogBinding.inflate(inflater, container, false)




        return binding.root
    }

}