package com.example.job_portal


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class free_page1 : AppCompatActivity() {

    private lateinit var btnNext1 : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_free_page1)
        supportActionBar?.hide()

        btnNext1 = findViewById(R.id.btnNext1)

        btnNext1.setOnClickListener{

            val intent = Intent(this,free_page2::class.java)
            startActivity(intent)
        }
    }
}