package com.example.job_portal

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class Seeker_profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seeker_profile)

        val imageButton = findViewById<ImageButton>(R.id.rcntbtn)

        imageButton.setOnClickListener {
            val intent = Intent(this, ReadData::class.java)
            startActivity(intent)
        }

        val category = findViewById<ImageButton>(R.id.catogry)

        category.setOnClickListener {
            val intent = Intent(this, JobView_Activity::class.java)
            startActivity(intent)
        }


        val home = findViewById<ImageButton>(R.id.imageView10)
        home.setOnClickListener {
            val intent = Intent(this, InquiryMainActivity::class.java)
            startActivity(intent)
        }


        val inq = findViewById<ImageButton>(R.id.imageView12)
        inq.setOnClickListener {
            val intent = Intent(this, InquiryDetailsActivity::class.java)
            startActivity(intent)
        }
    }
}

