package com.example.job_portal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.example.job_portal.databinding.ActivityCompanyDashBinding

class company_dash : AppCompatActivity() {

    private lateinit var binding: ActivityCompanyDashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCompanyDashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.createJob.setOnClickListener {
            val intent = Intent(this@company_dash,job_add::class.java)
            startActivity(intent)
            finish()
        }

        binding.viewJobs.setOnClickListener {
            val intent = Intent(this@company_dash,post_jobs::class.java)
            startActivity(intent)
            finish()
        }


        binding.notifi.setOnClickListener {
            val intent = Intent(this@company_dash,recieve_job_application::class.java)
            startActivity(intent)
            finish()
        }
    }
}