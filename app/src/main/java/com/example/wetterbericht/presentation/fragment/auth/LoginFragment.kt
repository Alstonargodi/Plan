package com.example.wetterbericht.presentation.fragment.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.wetterbericht.data.local.preferences.UserProfile
import com.example.wetterbericht.databinding.FragmentLoginBinding
import com.example.wetterbericht.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch

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

        viewModel.getProfilePreferences().observe(viewLifecycleOwner){ data->
            if (data.email != "nouserid"){
                binding.tvLoginChecker.visibility = View.VISIBLE
                binding.tvLoginChecker.text = data.email
            }
        }
        return binding.root
    }

    private fun loginAccount(){
        val email = binding.tvUsernameBox.text.toString()
        val password = binding.tvPasswordBox.text.toString()

        viewModel.authLoginUser(email,password)
            .addOnSuccessListener { data ->
               saveUserId(
                   data.user?.uid.toString(),
                   email
               )
            }
            .addOnFailureListener { e ->
                Log.d("login",e.message.toString())
            }
    }

    private fun saveUserId(userId : String,email : String){
        lifecycleScope.launch {
            viewModel.saveProfilePreferences(
                UserProfile(email,userId)
            )
        }
    }
}