package com.example.wetterbericht.fragment.util

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.wetterbericht.R
import com.example.wetterbericht.model.room.todo
import com.example.wetterbericht.viewmodel.room.todoviewmodel
import kotlinx.android.synthetic.main.fragment_addfragment.view.*
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [update.newInstance] factory method to
 * create an instance of this fragment.
 */
class updatefragment : Fragment() {
    private val args by navArgs<updatefragmentArgs>()
    lateinit var mroomviewmodel : todoviewmodel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mroomviewmodel = ViewModelProvider(this).get(todoviewmodel::class.java)
        view.et_up_title.setText(args.data.title)
        view.et_up_status.setText(args.data.status)
        view.et_up_deadline.setText(args.data.deadline)
        view.et_up_desc.setText(args.data.desc)

        view.btn_up_delete.setOnClickListener {
            deletedata()
        }

        view.btn_update.setOnClickListener {
            updatedata()
        }

        return view
    }

    private fun deletedata(){
        //warn
        val alert = AlertDialog.Builder(requireContext())
        alert.setPositiveButton("yes"){_,_ ->
            mroomviewmodel.delete(args.data)
            Toast.makeText(requireContext(),"delete complete",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updatefragment_to_fragment_todo)
        }
        alert.setNegativeButton("no"){_,_ ->}
        alert.setTitle("are u want delete this ?")
        alert.setMessage("choose")
        alert.create().show()
    }

    private fun updatedata(){
        val judul = et_up_title.text.toString()
        val status = et_up_status.text.toString()
        val deadline = et_up_deadline.toString()
        val desc = et_up_desc.text.toString()

        val update = todo(args.data.id,judul,status,deadline,desc)
        mroomviewmodel.update(update)

    }

}