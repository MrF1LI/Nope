package com.example.btuprojectnew.classes

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.btuprojectnew.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class RecyclerAdapter (private val subjectList: ArrayList<Subject>):
    RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseDatabase.getInstance().getReference("Students")

    class RecyclerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val name: TextView = itemView.findViewById(R.id.textViewSubject)
        val lecturer: TextView = itemView.findViewById(R.id.textViewLecturer)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.course_item, parent, false)
        return RecyclerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, @SuppressLint("RecyclerView") position: Int) {

        db.child(auth.currentUser!!.uid).child("Subjects").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                Log.d("TAGG", snapshot.toString())

                val currentSubject = subjectList[position]

                holder.name.text = currentSubject.name
                holder.lecturer.text = currentSubject.lecturer
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    override fun getItemCount() = subjectList.size

}