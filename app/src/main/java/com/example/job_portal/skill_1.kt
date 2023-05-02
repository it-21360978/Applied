package com.example.job_portal


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.cardview.widget.CardView

class skill_1 : AppCompatActivity() {

    private lateinit var btnGetstart1: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_skill1)
        supportActionBar?.hide()

        btnGetstart1 = findViewById(R.id.btninter)

        btnGetstart1.setOnClickListener{

            val intent = Intent(this,interview_1::class.java)
            startActivity(intent)
        }
    }
}