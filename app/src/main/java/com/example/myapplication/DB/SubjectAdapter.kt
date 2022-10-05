package com.example.myapplication.DB

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import kotlinx.android.synthetic.main.item_layout.view.*

class SubjectAdapter : RecyclerView.Adapter<SubjectAdapter.SubjectHolder>() {

    private var subjects : List<Subject> = ArrayList()

    class SubjectHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind(subject : Subject) = with(itemView){
            itemTitle.text = subject.title
            itemContent.text = subject.content
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectHolder {
        return SubjectHolder(LayoutInflater.from(parent.context)
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