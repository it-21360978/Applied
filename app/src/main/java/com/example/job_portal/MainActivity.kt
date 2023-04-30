package com.example.job_portal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        // Get the job data from the intent(company adapter eken data gannawa )
        val companyName = intent.getStringExtra("company_name")
        val location = intent.getStringExtra("location")
        val description = intent.getStringExtra("description")
        val age = intent.getStringExtra("age")
        val time = intent.getStringExtra("time")
        val requirements = intent.getStringExtra("description")

        // Update the UI with the job details(company adapter eken pass karana data xml eke view karanwa)
        val companyTextView = findViewById<TextView>(R.id.jobname)
        val locationTextView = findViewById<TextView>(R.id.jobCmpny)
        val ageTextView = findViewById<TextView>(R.id.joblocation)
        val descriptionTextView = findViewById<TextView>(R.id.decOfJob)
        val timeTextView = findViewById<TextView>(R.id.timejob)
        val requirement = findViewById<TextView>(R.id.jobrequrmnts)

        companyTextView.text = companyName
        locationTextView.text = location
        descriptionTextView.text = description
        ageTextView.text=age
        timeTextView.text=time
        requirement.text=requirements

        // Get a reference to the "Apply Now" button
        val applyButton = findViewById<Button>(R.id.applybtn)

        // Set a click listener for the button
        applyButton.setOnClickListener {
            // Create an intent to start the ApplyActivity
            val intent = Intent(this, JobForm_Activity::class.java)

            // Pass the job data to the ApplyActivity using intent extras
            intent.putExtra("company_name", companyName)
            intent.putExtra("location", location)
            intent.putExtra("description", description)
            intent.putExtra("age", age)
            startActivity(intent)
        }
    }
}
