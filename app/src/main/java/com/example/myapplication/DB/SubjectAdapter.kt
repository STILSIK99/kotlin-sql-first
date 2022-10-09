package com.example.myapplication.DB

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.SubjectActivity
import kotlinx.android.synthetic.main.item_layout.view.*

class SubjectAdapter(var contextM: Context) : RecyclerView.Adapter<SubjectAdapter.SubjectHolder>() {

    private var subjects : List<Subject> = ArrayList()



    class SubjectHolder(val contextH: Context, itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind(subject : Subject) = with(itemView){
            itemTitle.text = subject.title
            itemContent.text = subject.content
            itemView.setOnClickListener {
                val changeIntent = Intent(contextH, SubjectActivity::class.java).apply {
                    putExtra("Subject", subject)
                }
                contextH.startActivity(changeIntent)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectHolder {
        return SubjectHolder(contextM, LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: SubjectHolder, position: Int) {
        holder.bind(subjects[position])
    }

    override fun getItemCount(): Int {
        return subjects.size
    }

    fun refreshSubjects(subjects : List<Subject>){
        this.subjects = subjects
        notifyDataSetChanged()
    }

}