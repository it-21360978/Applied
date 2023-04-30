package com.example.job_portal

import CompanyAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


import com.example.job_portal.databinding.ActivityJobViewBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class JobView_Activity : AppCompatActivity() {

    /*   private lateinit var binding: ActivityJobViewBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CompanyAdapter
    private var jobList = ArrayList<company>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJobViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the RecyclerView
        recyclerView = binding.rvJob
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = CompanyAdapter(jobList)
        recyclerView.adapter = adapter
    }
}
*/
    private var dbref = FirebaseDatabase.getInstance().getReference("jobForms")
    private lateinit var jobRecyclerView: RecyclerView
    private lateinit var jobArrayList: ArrayList<company>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_view)

        jobRecyclerView = findViewById(R.id.rvJob)
        jobRecyclerView.layoutManager = LinearLayoutManager(this)
        jobRecyclerView.setHasFixedSize(true)

        jobArrayList = arrayListOf<company>()
        val companyAdapter = CompanyAdapter(jobArrayList)
        jobRecyclerView.adapter = companyAdapter

        getJobData()
    }

    private fun getJobData(){
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){
                    for (jobSnapshot in snapshot.children){
                        val job = jobSnapshot.getValue(company::class.java)

                        jobArrayList.add(job!!)

                    }
                    jobRecyclerView.adapter?.notifyDataSetChanged()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Do nothing for now
            }

        })
    }
}

   /* private var dbref = FirebaseDatabase.getInstance().getReference("jobForms")
    private lateinit var jobRecyclerView: RecyclerView
    private lateinit var jobArrayList: ArrayList<company>
    private lateinit var searchEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_view)

        jobRecyclerView = findViewById(R.id.rvJob)
        jobRecyclerView.layoutManager = LinearLayoutManager(this)
        jobRecyclerView.setHasFixedSize(true)

        // Initialize the search EditText
        searchEditText = findViewById(R.id.searchbar)

        jobArrayList = arrayListOf<company>()
        val companyAdapter = CompanyAdapter(jobArrayList)
        jobRecyclerView.adapter = companyAdapter

        getJobData()

        // Add a text listener to the search EditText
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Get the search query from the EditText
                val searchQuery = s.toString().toLowerCase()

                // Filter the jobArrayList based on the search query
                val filteredList = jobArrayList.filter { company ->
                    company.sfullName?.toLowerCase()?.contains(searchQuery) ?: false
                }

//                // Update the adapter with the filtered list
//                companyAdapter.updateList(filteredList)
            }

            override fun afterTextChanged(s: Editable?) {
                // Do nothing
            }
        })
    }

    private fun getJobData() {
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {
                    for (jobSnapshot in snapshot.children) {
                        val job = jobSnapshot.getValue(company::class.java)

                        jobArrayList.add(job!!)
                    }
                    jobRecyclerView.adapter?.notifyDataSetChanged()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Do nothing for now
            }
        })
    }
}*/

