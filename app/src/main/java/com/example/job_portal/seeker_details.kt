package com.example.job_portal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class seeker_details : AppCompatActivity() {

    lateinit var seekername: TextView
    lateinit var seekerjob: TextView
    lateinit var age: TextView
  /*  lateinit var category: TextView
    lateinit var salary: TextView
    lateinit var description: TextView
    lateinit var btnUpdate: Button
    lateinit var btnDelete: Button*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seeker_details)

            initView()
            setValuesToViews()

        }


        fun initView() {
            seekername = findViewById(R.id.seekername)
            seekerjob = findViewById(R.id.seekerjob)
            age = findViewById(R.id. age)
          /*  salary = findViewById(R.id. salary)
            title = findViewById(R.id.title)
            description = findViewById(R.id.description)


            btnUpdate = findViewById(R.id.btnUpdate)
            btnDelete = findViewById(R.id.btnDelete)*/
        }

        fun setValuesToViews() {
            seekername.text = intent.getStringExtra("fullName")
            seekerjob.text = intent.getStringExtra("jobName")
            age.text = intent.getStringExtra("address")
        /*    category.text = intent.getStringExtra("Ccategory")
            salary.text = intent.getStringExtra("Csalary")
            title.text = intent.getStringExtra("Ctitle")
            description.text = intent.getStringExtra("Cdescription")

        }*/
    }
}