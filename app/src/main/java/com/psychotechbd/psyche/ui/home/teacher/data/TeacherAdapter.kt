package com.psychotechbd.psyche.ui.home.teacher.data

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.psychotechbd.psyche.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_teacher_details.view.*
import kotlinx.android.synthetic.main.model_teacher_head.view.*

class TeacherAdapter(
    val context: Context,
    val data: List<TeacherItemList>,
    private val teacherItemListener: TeacherItemListener
) :
    RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder>() {

    //    studentItem ->> item count
    override fun getItemCount(): Int = data.size

    //inflate ->> model layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.model_teacher_head, parent, false)
        return TeacherViewHolder(view)
    }

    //holder model
    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {
        val itemPos: TeacherItemList = data[position]  //get position from array

        holder.textNameT.text = itemPos.name
        holder.textPost.text = itemPos.post
        Picasso.get().load(itemPos.imageUrl).into(holder.imageUrlT)

        holder.itemView.setOnClickListener {
            teacherItemListener.onItemClick(itemPos)
        }
    }

    // student view holder
    class TeacherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textNameT: TextView = itemView.textViewName_teacher_head
        var textPost: TextView = itemView.textView_post_teacher_head
        var imageUrlT: ImageView = itemView.imageView_teacher_head
    }

    interface TeacherItemListener {
        fun onItemClick(item: TeacherItemList)
    }
}

