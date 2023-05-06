package com.example.job_portal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.ActionBar
import com.example.job_portal.databinding.ActivityCompanyDashBinding

class company_dash : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company_dash)

        val createJob = findViewById<Button>(R.id.create_job)

        createJob.setOnClickListener {
            val intent = Intent(this@company_dash,job_add::class.java)
            startActivity(intent)
            finish()
        }

        val  viewJobs = findViewById<Button>(R.id.view_jobs)

        viewJobs.setOnClickListener {
            val intent = Intent(this@company_dash,post_jobs::class.java)
            startActivity(intent)
            finish()
        }

        val notifi = findViewById<Button>(R.id.notifi)

        notifi.setOnClickListener {
            val intent = Intent(this@company_dash,recieve_job_application::class.java)
            startActivity(intent)
            finish()
        }
    }
}