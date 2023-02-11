package com.example.wetterbericht.presentation.fragment.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.wetterbericht.R
import com.example.wetterbericht.databinding.FragmentLoginBinding
import com.example.wetterbericht.viewmodel.ViewModelFactory
import org.w3c.dom.Text

class LoginFragment : Fragment() {
    private val viewModel : LoginViewModel by viewModels{
        ViewModelFactory.getInstance(requireContext())
    }

    private lateinit var binding : FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)

        binding.btnLogin.setOnClickListener {
            loginAccount()
        }
        return binding.root
    }

    private fun loginAccount(){
        val email = binding.tvUsernameBox.text.toString()
        val password = binding.tvPasswordBox.text.toString()

        viewModel.authLoginUser(email,password)
            .addOnSuccessListener {
                Log.d("login","sucess")
            }
            .addOnFailureListener {
                Log.d("login","error")
            }
    }
}