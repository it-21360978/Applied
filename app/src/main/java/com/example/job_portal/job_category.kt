package com.example.job_portal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class job_category : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_category)

        val imageButton = findViewById<ImageButton>(R.id.vProfile)

        imageButton.setOnClickListener {
            val intent = Intent(this,Seeker_profile::class.java)
            startActivity(intent)
        }

        val home = findViewById<ImageButton>(R.id.vhome)
        home.setOnClickListener {
            val intent = Intent(this,activity_insertion::class.java)
            startActivity(intent)
        }

        val inq = findViewById<ImageButton>(R.id.vInqury)
        inq.setOnClickListener {
            val intent = Intent(this, InquiryMainActivity::class.java)
            startActivity(intent)
        }
    }
}