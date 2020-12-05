package com.psychotechbd.psyche.registration

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.psychotechbd.psyche.MainActivity
import com.psychotechbd.psyche.R
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*


class Login : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        view.tv_newReg_mLog.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(R.id.action_login_to_signUp1)
        }

        view.btn_login_mLog.setOnClickListener {
            perFormLogin()
        }

        return view
    }

    private fun perFormLogin() {
        val email = et_email_mLog.text.toString()
        val password = et_password_mLog.text.toString()

        if (!validEmail()|| ! validPassword()) {
            return
        }
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Toast.makeText(requireContext(), "login Successfully", Toast.LENGTH_SHORT).show()
                if (!it.isSuccessful) return@addOnCompleteListener
                val intent = Intent(requireActivity(), MainActivity::class.java)
                startActivity(intent)
                requireActivity().finish()

            }
            .addOnFailureListener {
                Log.d("login", "Login Failed :$it")
                Toast.makeText(requireContext(), "invalid Email or password\n( Enter Correct email & password )", Toast.LENGTH_LONG).show()
            }
    }
    // email validation
    private fun validEmail(): Boolean {
        val value = et_email_mLog.text.toString()

        return when {
            value.isEmpty() -> {
                ti_email_mLog.error = "Enter Email Address"
                false
            }

            else -> {
                ti_email_mLog.error = null
                true
            }
        }
    }

    //password validation
    private fun validPassword(): Boolean {
        val value = et_password_mLog.text.toString()

        return when {
            value.isEmpty() -> {
                ti_password_mLog.error = "Enter Password"
                false
            }
            value.length < 8 -> {
                ti_password_mLog.error = "Password should be 8 digit"
                false
            }
            else -> {
                ti_password_mLog.error = null
                true
            }
        }
    }
}