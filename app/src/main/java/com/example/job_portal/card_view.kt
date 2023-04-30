package com.example.job_portal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class card_view : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_view)

        // Get a reference to the "find Another job" button
        val applyButton = findViewById<Button>(R.id.btnRejob)

        // Set a click listener for the button
        applyButton.setOnClickListener {
            // Create an intent to start the ApplyActivity
            val intent = Intent(this,JobView_Activity::class.java)
            startActivity(intent)
        }
    }
}