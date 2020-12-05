package com.psychotechbd.psyche.ui.home.student.ui.data

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.psychotechbd.psyche.R
import kotlinx.android.synthetic.main.model_student.view.*

class StudentAdapter(
    val context: Context,
    val data: List<StudentItemList>) :
    RecyclerView.Adapter<StudentAdapter.MyViewHolder>() {

    // onCount
    override fun getItemCount(): Int = data.size

    // onCreateView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.model_student, parent, false)
        return MyViewHolder(view)
    }

    // onBind
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val mPosition:StudentItemList = data[position]
        holder.tvName.text = mPosition.name
        holder.tvId.text = "Id: ${mPosition.id}"
        holder.tvSession.text = "Session: ${mPosition.session}"

        // view image
        holder.ivProfile.load(mPosition.imageUrl) {
            crossfade(true)
            placeholder(R.drawable.placeholder)
            transformations(CircleCropTransformation())
        }
    }

    // view holder
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.tv_model_student_name
        val tvId: TextView = itemView.tv_model_student_id
        val tvSession: TextView = itemView.tv_model_student_session
        val ivProfile: ImageView = itemView.iv_model_student_image
    }
}