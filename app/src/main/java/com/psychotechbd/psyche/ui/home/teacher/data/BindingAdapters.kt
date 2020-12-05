package com.psychotechbd.psyche.ui.home.teacher.data

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, imageUrl: String) {
    Glide.with(view.context)
        .load(imageUrl)
        .into(view)
}

//@BindingAdapter("mobileNumber")
//fun callTeacher(callBtn: Button, mobile:String){
//    callBtn.setOnClickListener { v: View ->
//        val mobileNO = mobile
//        val call = "tel:$mobileNO"
//        val intent = Intent(Intent.ACTION_DIAL)
//        intent.data = Uri.parse(call)
//        v.context.startActivity(intent)
//        Toast.makeText(v.context, "Open Dialer", Toast.LENGTH_SHORT).show()
//    }
//}




