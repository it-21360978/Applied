package com.example.job_portal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class RegisteredUsersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registered_users)
        val count = intent.getIntExtra("registeredUsersCount", 0)

        val countTextView = findViewById<TextView>(R.id.countTextView)
        countTextView.text = "Registered Users: $count"


    }
}