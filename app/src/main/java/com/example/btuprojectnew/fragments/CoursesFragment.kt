package com.example.btuprojectnew.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.btuprojectnew.R
import com.example.btuprojectnew.classes.RecyclerAdapter
import com.example.btuprojectnew.classes.Subject
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class CoursesFragment: Fragment (R.layout.fragment_courses) {

    private lateinit var db: DatabaseReference
    private lateinit var subjectRecyclerView: RecyclerView
    private lateinit var subjectArrayList: ArrayList<Subject>
    private var auth = FirebaseAuth.getInstance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subjectRecyclerView = view.findViewById(R.id.subjectList)
        subjectRecyclerView.layoutManager = LinearLayoutManager(activity)
        subjectRecyclerView.setHasFixedSize(true)

        subjectArrayList = arrayListOf<Subject>()
        getSubjectData()

    }

    private fun getSubjectData() {
        db = FirebaseDatabase.getInstance().getReference("Students")
        db.child(auth.currentUser!!.uid).child("Subjects").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {

                    for (subjectSnapshot in snapshot.children) {
                        val subject = subjectSnapshot.getValue(Subject::class.java)?: return
                        subjectArrayList.add(subject)
                    }

                    subjectRecyclerView.adapter = RecyclerAdapter(subjectArrayList)

                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

}