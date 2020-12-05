package com.psychotechbd.psyche.registration

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.psychotechbd.psyche.MainActivity
import com.psychotechbd.psyche.R
import com.psychotechbd.psyche.databinding.FragmentSignUp2Binding
import kotlinx.android.synthetic.main.fragment_sign_up.*
import java.util.*
import java.util.concurrent.TimeUnit

class SignUp2 : Fragment() {

    private var _binding: FragmentSignUp2Binding? = null
    private val binding get() = _binding!!

    private lateinit var batch: String
    private lateinit var id: String
    private lateinit var hall: String
    private lateinit var mobile: String

    private lateinit var email: EditText
    private lateinit var password: EditText

    lateinit var ref: DatabaseReference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        _binding = FragmentSignUp2Binding.inflate(inflater, container, false)

        val sharedPref = requireActivity().getSharedPreferences("SignUp1", Context.MODE_PRIVATE)
        batch = sharedPref.getString("batch", "").toString()
        id = sharedPref.getString("id", "").toString()
        hall = sharedPref.getString("hall", "").toString()
        mobile = sharedPref.getString("mobile", "").toString()

        binding.apply {
            email = etSignUp2Email
            password = etSignUp2Password
        }

        binding.btnSignUp2SignUp.setOnClickListener {

            performRegister()
            val intent = Intent(requireActivity(), MainActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    private fun performRegister() {

//        if (!validToken() || !validBatch() || !validStdId() || !validEmail() || !validPassword()) {
//            return
//        }

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(
            email.text.toString(), password.text.toString()
        )
            //create user
            .addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener
                Log.d("sign", "Email Authentication successful ")
                uploadToFirebase()

            }.addOnFailureListener {
                Toast.makeText(
                    requireContext(),
                    "Email Authentication Failed: $it", Toast.LENGTH_LONG
                ).show()
                Log.e("sign", "Email Authentication Failed: ${it.message}")
            }
    }

    private fun uploadToFirebase() {
        val db = FirebaseDatabase.getInstance().getReference("Student")
        ref = db.child(batch).child(id)

//        when (batch) {
//            "Batch 15" -> {
//                ref = db.child("1st Year").child(id)
//            }
//            "Batch 14" -> {
//                ref = db.child("2nd Year").child(id)
//            }
//            "Batch 13" -> {
//                ref = db.child("3rd Year").child(id)
//            }
//            "Batch 12" -> {
//                ref = db.child("4th Year").child(id)
//            }
//        }
        val map = hashMapOf<String, Any>()
        map["hall"] = hall
        map["mobile"] = mobile

        ref.updateChildren(map).addOnCompleteListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }
            .addOnFailureListener {
                Toast.makeText(requireActivity(), "Data not save:${it.message}", Toast.LENGTH_SHORT)
                    .show()
            }
    }


}