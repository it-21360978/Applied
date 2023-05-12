package com.example.job_portal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import com.example.job_portal.databinding.ActivityCompanyDashBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class company_dash : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company_dash)

        val createJob = findViewById<Button>(R.id.create_job)
        val tvJobCount = findViewById<TextView>(R.id.tvJobCount)
        val rvJobCount = findViewById<TextView>(R.id.rvJobCount)

        val comId = intent.getStringExtra("comId")
        var dbRef = FirebaseDatabase.getInstance().getReference("Jobs")
        dbRef.orderByChild("CcomId").equalTo(comId).addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val jobCount = snapshot.childrenCount.toInt()
                tvJobCount.text = "Post Jobs: $jobCount"
            }



            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        val fullName = intent.getStringExtra("fullName")
        var sdbRef = FirebaseDatabase.getInstance().getReference("jobForms")
        sdbRef.orderByChild("sfullName").equalTo(fullName).addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val rjobCount = snapshot.childrenCount.toInt()
                rvJobCount.text = "Recieve Job Application: $rjobCount"
            }



            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })


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


        val home = findViewById<ImageButton>(R.id.vhome)
        home.setOnClickListener {
            val intent = Intent(this,InquiryMainActivity::class.java)
            startActivity(intent)
        }

        val inq = findViewById<ImageButton>(R.id.vInqury)
        inq.setOnClickListener {
            val intent = Intent(this,activity_insertion::class.java)
            startActivity(intent)
        }
        val vCategory=findViewById<ImageButton>(R.id.vCategory)
        vCategory.setOnClickListener {
            val intent = Intent(this,job_category::class.java)
            startActivity(intent)
        }

    }
}