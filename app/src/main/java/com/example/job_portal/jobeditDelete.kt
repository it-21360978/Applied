package com.example.job_portal

import android.content.Intent
import android.os.Bundle
import android.widget.Button

import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class jobeditDelete : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jobedite_delete)

        lateinit var databaseRef: DatabaseReference
        lateinit var jobNameTextView: TextView
        lateinit var fullNameTextView: TextView
        lateinit var emailTextView: TextView
        lateinit var genderTextView: TextView
        lateinit var addressTextView: TextView
        lateinit var mobileTextView: TextView
        lateinit var editButton: Button
        lateinit var deleteButton: Button
        //lateinit var jobId: String

        // Initialize the database reference
        databaseRef = FirebaseDatabase.getInstance().reference.child("jobForms")

        // Get references to the UI elements
        jobNameTextView = findViewById(R.id.tvjob)
        fullNameTextView = findViewById(R.id.tvName)
        emailTextView = findViewById(R.id.tvEmail)
        //genderTextView = findViewById(R.id.)
        addressTextView = findViewById(R.id.tvAddress)
        mobileTextView = findViewById(R.id.tvMobile)
        editButton = findViewById(R.id.btnedit)
        deleteButton = findViewById(R.id.btndelete)

        // Get the job ID from the intent
        //jobId = intent.getStringExtra("jobId") ?: ""
        val jobName = intent.getStringExtra("jobName") ?: ""


        // Fetch the job data from the database
        databaseRef.child(jobName).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // Get the job object from the snapshot
                val job = snapshot.getValue(Job::class.java)

                // Update the UI with the job details
                jobNameTextView.text = job?.jobName
                fullNameTextView.text = job?.sFullName
                emailTextView.text = job?.sEmail
               // genderTextView.text = if (job?.sGender == true) "Male" else "Female"
                addressTextView.text = job?.sAddress
                mobileTextView.text = job?.sMobile
            }

            override fun onCancelled(error: DatabaseError) {
                // Show an error message if the job data could not be fetched
                TODO("Not yet implemented")
            }
        })

        // Set up the edit button click listener
        editButton.setOnClickListener {
            // Start the JobFormActivity with the job ID and job data
            val intent = Intent(this, JobForm_Activity::class.java)
            intent.putExtra("jobName", jobName)
            startActivity(intent)
        }

        // Set up the delete button click listener
        deleteButton.setOnClickListener {
            // Delete the job data from the database
            databaseRef.child(jobName).removeValue().addOnSuccessListener {
                // Show a success message
                Toast.makeText(this, "Job deleted successfully", Toast.LENGTH_SHORT).show()
                // Finish the activity
                finish()
            }.addOnFailureListener {
                // Show an error message
                Toast.makeText(this, "Failed to delete job", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
