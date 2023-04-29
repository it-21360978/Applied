package com.example.job_portal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


import com.example.job_portal.databinding.ActivityJobViewBinding

class JobView_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityJobViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJobViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.applybtn.setOnClickListener{
            val intent = Intent(this@JobView_Activity,JobForm_Activity::class.java)
            startActivity(intent)
        }
    }
}


