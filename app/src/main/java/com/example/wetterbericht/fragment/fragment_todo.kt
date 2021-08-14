package com.example.wetterbericht.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.wetterbericht.R
import com.example.wetterbericht.fragment.util.addfragment
import kotlinx.android.synthetic.main.fragment_todo.*
import kotlinx.android.synthetic.main.fragment_todo.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [homeweather.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_todo : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_todo, container, false)


        view.btn_add.setOnClickListener {
            var customdialog = addfragment()

            customdialog.showsDialog

            val sp = (activity as AppCompatActivity).supportFragmentManager
            val adfragment = addfragment()

            adfragment.show(sp,"dialogadd")

//           findNavController().navigate(R.id.action_fragment_todo_to_addfragment)
        }

        return view
    }

}