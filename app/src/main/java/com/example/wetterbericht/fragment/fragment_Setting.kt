package com.example.wetterbericht.fragment

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.wetterbericht.R
import com.example.wetterbericht.viewmodel.room.todoviewmodel
import kotlinx.android.synthetic.main.fragment_setting.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_Setting.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_Setting : Fragment() {
    lateinit var mviewmodel : todoviewmodel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_setting, container, false)
        mviewmodel = ViewModelProvider(this).get(todoviewmodel::class.java)

        view.sp_unit.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
               Toast.makeText(context,"set ${parent?.getItemAtPosition(position).toString()}",Toast.LENGTH_SHORT).show()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
               // Not choosing fun
            }

        }

        //todo forecast
        view.btn_deletetodo.setOnClickListener {
            val alert = AlertDialog.Builder(requireContext())
            alert.setPositiveButton("yes"){_,_ ->
                mviewmodel.deleteall()
                Toast.makeText(requireContext(),"delete complete",Toast.LENGTH_SHORT).show()
            }
            alert.setNegativeButton("no"){_,_ ->}
            alert.setTitle("Are u sure want to delete all?")
            alert.setMessage("choose")
            alert.create().show()
        }

        return view
    }
}