package com.example.wetterbericht.view.todo.dialog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.wetterbericht.R
import com.example.wetterbericht.databinding.FragmentInsertTagBinding
import com.flask.colorpicker.builder.ColorPickerDialogBuilder


class InsertTagFragment : DialogFragment() {
    private lateinit var binding : FragmentInsertTagBinding
    private lateinit var tagCallBack : onTagCallback
    private var color : Int = 0

    fun onTagCallBack(callback : onTagCallback){
        this.tagCallBack = callback
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInsertTagBinding.inflate(layoutInflater)

        binding.colorPickerView.addOnColorSelectedListener {
            binding.etNewTagColour.setTextColor(it)
            color = it
        }
        binding.btnaddTag.setOnClickListener {
            addNewTag()
            this.dismiss()
        }

        return binding.root
    }

    private fun addNewTag(){
        val name = binding.etNewTagName.text.toString()

        tagCallBack.tagCallBack(name,color)
    }


    interface onTagCallback{
        fun tagCallBack(name : String,color : Int)
    }


}