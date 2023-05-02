package com.example.job_portal

import Job
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.google.firebase.database.*


    @Suppress("DEPRECATION")
    class jobeditDelete : AppCompatActivity() {


        private lateinit var fullName: EditText
        private lateinit var email: EditText
        private lateinit var address: EditText
        private lateinit var mobile: EditText
        private lateinit var jobName: EditText
        private lateinit var CVUrl: EditText
        private lateinit var btnedit: Button
        private lateinit var btnDelete: Button
        private lateinit var job: Job

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_jobedite_delete)


            // Get the Job object passed from the ReadData activity
            job = intent.getParcelableExtra("job") ?: Job()



            // Get references to the UI components
            fullName = findViewById(R.id.tvName)
            email = findViewById(R.id.tvEmail)
            address = findViewById(R.id.tvAddress)
            mobile = findViewById(R.id.tvMobile)
            jobName = findViewById(R.id.tvjob)
            CVUrl = findViewById(R.id.choosefile)
            btnedit = findViewById(R.id.btnedit)
            btnDelete = findViewById(R.id.btndelete)

            // Set the UI components to display the retrieved data
            fullName.setText(job.fullName).toString()
            email.setText(job.email).toString()
            address.setText(job.address).toString()
            mobile.setText(job.mobile).toString()
            jobName.setText(job.jobName).toString()
            CVUrl.setText(job.cvUrl).toString()

            // Set an OnClickListener on the "Save" button to update the data in Firebase
            btnedit.setOnClickListener {
                // Update the Job object with the new data
                job.fullName = fullName.text.toString()
                job.email = email.text.toString()
                job.address = address.text.toString()
                job.mobile = mobile.text.toString()
                job.jobName = jobName.text.toString()
                job.cvUrl = CVUrl.text.toString()

                // Get a reference to the "jobForms" node in your database
                val database = FirebaseDatabase.getInstance()
                val jobFormsRef = database.getReference("jobForms")

                // Update the data for the child node corresponding to the current job
                jobFormsRef.child(job.fullName).setValue(job)
                    .addOnSuccessListener {
                        // Show a success message
                        Toast.makeText(
                            this@jobeditDelete,
                            "Data updated successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    .addOnFailureListener {
                        // Show an error message
                        Toast.makeText(
                            this@jobeditDelete,
                            "Failed to update data: ${it.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }

            // Set an OnClickListener on the "Delete" button to delete the data from Firebase
            btnDelete.setOnClickListener {
                // Get a reference to the "jobForms" node in your database
                val database = FirebaseDatabase.getInstance()
                val jobFormsRef = database.getReference("jobForms")

                // Delete the child node corresponding to the current job
                jobFormsRef.child(job.fullName).removeValue()
                    .addOnSuccessListener {
                        // Show a success message
                        Toast.makeText(
                            this@jobeditDelete,
                            "Data deleted successfully",
                            Toast.LENGTH_SHORT
                        ).show()

                        // Finish the activity to return to the ReadData activity
                        finish()
                    }
                    .addOnFailureListener {
                        // Show an error message
                        Toast.makeText(
                            this@jobeditDelete,
                            "Failed to delete data: ${it.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }
        }
    }


