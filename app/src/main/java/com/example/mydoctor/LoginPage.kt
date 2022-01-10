package com.example.mydoctor

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mydoctor.databinding.FragmentLoginPageBinding


class LoginPage : Fragment() {

    private var _binding: FragmentLoginPageBinding? = null
    private val binding: FragmentLoginPageBinding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentLoginPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val sharedPreferences =
            requireContext().getSharedPreferences("doctor", Context.MODE_PRIVATE)

        if (sharedPreferences.getBoolean("isLoggedIn", false)) {
            val directions =
                LoginPageDirections.actionLoginPageToHomePage(
                    sharedPreferences.getString(
                        "username",
                        "NA"
                    ) ?: "NA"
                )
            findNavController().navigate(directions)
        }

        binding.loginEditText.addTextChangedListener {
            if (it?.toString()?.isNotEmpty() == true)
                binding.userNameTextInput.error = null
        }

        binding.passwordTextEdit.addTextChangedListener {
            if (it?.toString()?.isNotEmpty() == true)
                binding.passwordTextInput.error = null
        }

        binding.loginButton.setOnClickListener {
            if (binding.loginEditText.text.toString().isNotEmpty()) {
                if (binding.passwordTextEdit.text.toString().isNotEmpty()) {
                    val username = binding.loginEditText.text.toString()
                    sharedPreferences.edit {
                        putBoolean("isLoggedIn", true)
                        putString("username", username)
                    }
                    val directions =
                        LoginPageDirections.actionLoginPageToHomePage(username)
                    findNavController().navigate(directions)
                } else binding.passwordTextInput.error = "Please enter password"
            } else binding.userNameTextInput.error = "Please enter username"
        }

    }

    companion object {
        private const val TAG = "LoginPage"
    }
}