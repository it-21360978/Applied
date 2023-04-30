import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.job_portal.R
import com.example.job_portal.company
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

/*
package com.example.job_portal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class job_list : AppCompatActivity() {

    private val dbref = FirebaseDatabase.getInstance()
    private lateinit var jobRecyclerView: RecyclerView
    private lateinit var  jobArrayList: ArrayList<job_list>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_list)

        jobRecyclerView = findViewById(R.id.tvCompany)
        jobRecyclerView.layoutManager = LinearLayoutManager(this)
        jobRecyclerView.setHasFixedSize(true)
        jobArrayList = arrayListOf<job_list>()
        getJobData()
    }

    private fun getJobData(){
        dbref = FirebaseDatabase.getInstance().getReference("jobs")

        dbref.addValueEventListner(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){
                    for (jobSnapshot in snapshot.children){
                        val job = jobSnapshot.getValue(job_list::class.java)

                        jobArrayList.add(job!!)

                    }
                    jobRecyclerView.adapter = CompanyAdapter(jobArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}*//*

package com.example.job_portal
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class job_list : AppCompatActivity() {

    private var dbref = FirebaseDatabase.getInstance().getReference("jobForms")
    private lateinit var jobRecyclerView: RecyclerView
    private lateinit var  jobArrayList: ArrayList<company>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_list)

        jobRecyclerView = findViewById(R.id.tvCompany)
        jobRecyclerView.layoutManager = LinearLayoutManager(this)
        jobRecyclerView.setHasFixedSize(true)
        jobArrayList = arrayListOf<company>()
        getJobData()
    }

    private fun getJobData(){
        dbref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){
                    for (jobSnapshot in snapshot.children){
                        val job = jobSnapshot.getValue(company::class.java)

                        jobArrayList.add(job!!)

                    }
                    jobRecyclerView.adapter = CompanyAdapter(jobArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Do nothing for now
            }

        })
    }
}
*/
/*
package com.example.job_portal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class job_list : AppCompatActivity() {

    private var dbref = FirebaseDatabase.getInstance().getReference("jobForms")
    private lateinit var jobRecyclerView: RecyclerView
    private lateinit var jobArrayList: ArrayList<company>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_list)

        jobRecyclerView = findViewById(R.id.tvCompany)
        jobRecyclerView.layoutManager = LinearLayoutManager(this)
        jobRecyclerView.setHasFixedSize(true)
        jobArrayList = arrayListOf<company>()
        getJobData()
    }

    private fun getJobData() {
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (jobSnapshot in snapshot.children) {
                        val job = jobSnapshot.getValue(company::class.java)
                        jobArrayList.add(job!!)
                    }
                    jobRecyclerView.adapter = CompanyAdapter(jobArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Do nothing for now
            }
        })
    }
}
*/

class job_list : AppCompatActivity() {

    private var dbref = FirebaseDatabase.getInstance().getReference("jobForms")
    private lateinit var jobRecyclerView: RecyclerView
    private lateinit var jobArrayList: ArrayList<company>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_list)

        jobRecyclerView = findViewById(R.id.tvCompany)
        jobRecyclerView.layoutManager = LinearLayoutManager(this)
        jobRecyclerView.setHasFixedSize(true)
        jobArrayList = arrayListOf<company>()
        getJobData()
    }

    private fun getJobData() {
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (jobSnapshot in snapshot.children) {
                        val job = jobSnapshot.getValue(company::class.java)
                        jobArrayList.add(job!!)
                    }
                    val adapter = CompanyAdapter(jobArrayList)
                    jobRecyclerView.adapter = adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Do nothing for now
            }
        })
    }
}
