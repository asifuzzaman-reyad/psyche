package com.psychotechbd.psyche.registration

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.psychotechbd.psyche.R
import com.psychotechbd.psyche.databinding.FragmentSignUp1Binding

class SignUp1 : Fragment() {

    private var _binding: FragmentSignUp1Binding? = null
    private val binding get() = _binding!!

    private lateinit var tokenEt: EditText
    private lateinit var batchAutoCom: EditText
    private lateinit var idAutoCom: EditText
    private lateinit var idAutoComLayout: TextInputLayout
    private lateinit var hallAutoCom: EditText
    private lateinit var mobileAutoCom: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSignUp1Binding.inflate(inflater, container, false)

        binding.apply {
            tokenEt = etSignUp1Token
            batchAutoCom = acSignUp1Batch
            idAutoCom = acSignUp1Id
            idAutoComLayout = tilSignUp1Id
            hallAutoCom = acSignUp1Hall
            mobileAutoCom = etSignUp1Mobile
        }

        loadBatchAndId()
        loadHall()

        binding.btnSignUp1Next.setOnClickListener {

            sharePrefMobile(
                batchAutoCom.text.toString(),
                idAutoCom.text.toString(),
                hallAutoCom.text.toString(),
                mobileAutoCom.text.toString(),
            )

            val alert = AlertDialog.Builder(requireActivity())

            data class StudentItems(
                var name: String? = "",
                var id: String? = "",
                var mobile: String? = "",
                var session: String? = "",
            ) {
                constructor() : this("", "", "", "")
            }

            //
            val sharedPref: SharedPreferences = requireActivity().getSharedPreferences("SignUp1", Context.MODE_PRIVATE)
            val batch = sharedPref.getString("batch", "").toString()

            val db = FirebaseDatabase.getInstance().getReference("Student").child(batch)
            db.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    snapshot.children.forEach {
                        val key = it.key.toString()
                        Log.i("key", key)

                        val value = it.getValue(StudentItems::class.java)
                        Log.i("key2", value!!.session.toString())


                        if (idAutoCom.text.toString() == key &&
                            value.session.toString() == tokenEt.text.toString()
                        ) {
                            Log.i("key1", " key Match")
                            findNavController().navigate(R.id.action_signUp1_to_signUp2)

                        } else {
                            Toast.makeText(
                                requireActivity(),
                                "Id not found in database",
                                Toast.LENGTH_SHORT
                            ).show()
                            Log.i("key1", " sorry bro")
                        }

                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
        }

        return binding.root
    }


    // auto complete text view
    private fun loadBatchAndId() {

        //list
        val batch = listOf("Batch 15", "Batch 14", "Batch 13", "Batch 12")
        val batch15 = listOf("206080", "196080")
        val batch14 = listOf("196080", "186080")
        val batch13 = listOf("186080", "176080")
        val batch12 = listOf("176080", "166080")

        //array adapter
        val adapterBatch = ArrayAdapter(requireContext(), R.layout.material_spinner_item, batch)
        val adapterBatch15 = ArrayAdapter(requireContext(), R.layout.material_spinner_item, batch15)
        val adapterBatch14 = ArrayAdapter(requireContext(), R.layout.material_spinner_item, batch14)
        val adapterBatch13 = ArrayAdapter(requireContext(), R.layout.material_spinner_item, batch13)
        val adapterBatch12 = ArrayAdapter(requireContext(), R.layout.material_spinner_item, batch12)

        //item click listener
        (batchAutoCom as AutoCompleteTextView?)?.setAdapter(adapterBatch)
        (batchAutoCom as AutoCompleteTextView?)?.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, position, l ->

                when (position) {
                    0 -> {
                        idAutoCom.setText("")
                        idAutoComLayout.visibility = View.VISIBLE
                        (idAutoCom as AutoCompleteTextView?)?.setAdapter(adapterBatch15)

                    }
                    1 -> {
                        idAutoCom.setText("")
                        idAutoComLayout.visibility = View.VISIBLE
                        (idAutoCom as AutoCompleteTextView?)?.setAdapter(adapterBatch14)
                    }
                    2 -> {
                        idAutoCom.setText("")
                        idAutoComLayout.visibility = View.VISIBLE
                        (idAutoCom as AutoCompleteTextView?)?.setAdapter(adapterBatch13)
                    }
                    3 -> {
                        idAutoCom.setText("")
                        idAutoComLayout.visibility = View.VISIBLE
                        (idAutoCom as AutoCompleteTextView?)?.setAdapter(adapterBatch12)
                    }
                }
            }



    }

    private fun loadHall() {
        //list
        val hallList = listOf(
            "Shaheed Abdur Rab Hall", "Pritilata Hall", "Shamsun Nahar Hall",
            "Deshnetri Begum Khaleda Zia Hall", "Bangamata Sheikh Fazilatunnesa Mujib Hall",
            "Jononetri Sheikh Hasina Hall"
        )
        //array adapter
        val adapterHall = ArrayAdapter(requireContext(), R.layout.material_spinner_item, hallList)
        (hallAutoCom as AutoCompleteTextView?)?.setAdapter(adapterHall)
    }

    //
    private fun sharePrefMobile(batch: String, id: String, hall: String, mobile: String) {
        val sharedPreferences =
            requireActivity().getSharedPreferences("SignUp1", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor?.putString("batch", batch)
        editor?.putString("id", id)
        editor?.putString("hall", hall)
        editor?.putString("mobile", mobile)
        editor?.apply()
    }

}