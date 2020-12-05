package com.psychotechbd.psyche.registration

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.psychotechbd.psyche.MainActivity
import com.psychotechbd.psyche.R
import kotlinx.android.synthetic.main.fragment_sign_up.*
import kotlinx.android.synthetic.main.fragment_sign_up.view.*
import java.util.*

class SignUp : Fragment() {

    private var token: String? = null
    private var batch: String? = null
    private var stdId: String? = null
    private var email: String? = null
    private var password: String? = null
    var check: DatabaseReference? = null

    var key: String? = null
    var keyToken: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)

        performSpinner(view) ////spinner year

        view.btn_signIn_mSign.setOnClickListener {
//            performRegister()
//            Navigation.findNavController(requireView()).navigate(R.id.)
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        view.tv_already_mSign.setOnClickListener {
//            Navigation.findNavController(requireView()).navigate(R.id.)
        }

//        check= FirebaseDatabase.getInstance().getReference("student").child(batch!!.toLowerCase(Locale.ROOT))

        return view
    }

    private fun performRegister() {
        token = et_name_mSign.text.toString().trim()
        batch = tv_auto_Com.text.toString()
        stdId = tv_dep_Id_mSign.text.toString()
        email = et_email_mSign.text.toString()
        password = et_password_mSign.text.toString()

        val dbCheck = FirebaseDatabase.getInstance()
            .getReference("/student/${batch?.toLowerCase(Locale.ROOT)}")
        val refCheck = dbCheck.orderByKey().equalTo(stdId)

        if (!validToken() || !validBatch() || !validStdId() || !validEmail() || !validPassword()) {
            return
        }

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email!!, password!!)
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
        val uid = FirebaseAuth.getInstance().uid ?: ""

        val ref = FirebaseDatabase.getInstance().getReference("/users/${batch?.toLowerCase(Locale.ROOT)}/$stdId")

//        val db =
//            FirebaseDatabase.getInstance().getReference("/users/${batch?.toLowerCase(Locale.ROOT)}")
//        val ref = db.orderByChild("id").equalTo(stdId)

        val user = UserHelper(
            token!!, stdId!!, email!!, password!!, uid
        )


//        ref.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if (snapshot.exists()) {
//
//                } else {
//
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Toast.makeText(
//                    requireContext(),
//                    "Sign Up Error: ${error.message}",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }

//        })
                ref.setValue(user)
                .addOnSuccessListener {
                Toast.makeText(requireContext(), "Sign Up Successfully", Toast.LENGTH_SHORT).show()
//                   Navigation.findNavController(requireView()).navigate(R.id.action_signUp_to_navigation_home)
            }
    }


    /////////////////////////////////////////////////////////
    private fun validToken(): Boolean {
        val value = et_name_mSign.text.toString()

        return when {
            value.isEmpty() -> {
                ti_name_mSign.error = " Enter User Token First"
                false
            }
//            value != keyToken.toString() -> {
//                ti_name_mSign.error = " Invalid User Token ! Enter correct one "
//                false
//            }
            else -> {
                ti_name_mSign.error = null
//                ti_name_mSign.isErrorEnabled = false
                true
            }
        }
    }

    //
    private fun validBatch(): Boolean {
        batch = tv_auto_Com.text.toString()

        return when {
            batch!!.isEmpty() -> {
                ti_auto_com.error = " Enter year"
                false
            }

            else -> {
                ti_auto_com.error = null
                true
            }
        }
    }

    //
    private fun validStdId(): Boolean {

        val dbCheck = FirebaseDatabase.getInstance()
            .getReference("/student/${batch?.toLowerCase(Locale.ROOT)}")
        val refCheck = dbCheck.orderByKey().equalTo(stdId)

        val value = tv_dep_Id_mSign.text.toString()
        var words: List<String>? = null
        words = listOf(
            "17608047", "17608026", "17608044",
            "18608047", "18608026", "18608044",
            "19608047", "19608026", "19608044",
            "20608047", "20608026", "20608044",
        )

        val pattern = value.toRegex()
        var match: String? = null

        words.forEach { word ->
            if (pattern.containsMatchIn(word)) {
                match = word
            }
        }

        return when {
            value.isEmpty() -> {
                ti_dep_id_mSign.error = "Enter your Student ID"
                false
            }
            value.length < 8 -> {
                ti_dep_id_mSign.error = "student Id must be 8 Digit"
                false
            }
            value.equals(refCheck) -> {
                ti_dep_id_mSign.error = null
                false
            }
            else -> {
                ti_dep_id_mSign.error = "Student Id not match"
                true
            }
        }
    }

    //
    private fun validEmail(): Boolean {
        val value = et_email_mSign.text.toString()

        return when {
            value.isEmpty() -> {
                ti_email_mSign.error = "Enter your Email"
                false
            }

            else -> {
                ti_email_mSign.error = null
                true
            }
        }
    }

    //
    private fun validPassword(): Boolean {
        val value = et_password_mSign.text.toString()

        return when {
            value.isEmpty() -> {
                ti_password_mSign.error = "Enter your Password"
                false
            }
            value.length < 8 -> {
                ti_password_mSign.error = "Password should be 8 digit"
                false
            }
            else -> {
                ti_password_mSign.error = null
                true
            }
        }
    }

    private fun performSpinner(view: View) {
        val spinnerItems = listOf(
            "Batch 15", "Batch 14", "Batch 13", "Batch 12"
        )

        val adapter = ArrayAdapter(requireContext(), R.layout.material_spinner_item, spinnerItems)
        (view.ti_auto_com.editText as? AutoCompleteTextView)?.setAdapter(adapter)
    }
}