package com.example.job_portal



import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.cardview.widget.CardView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class InquiryMainActivity : AppCompatActivity() {

    private lateinit var btnInsertData: CardView

    lateinit var btnfree: CardView
    lateinit var btnI: CardView
    private lateinit var btnprofile: CardView
    private lateinit var Jobview: CardView







    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)
        supportActionBar?.hide()

        val firebase :DatabaseReference = FirebaseDatabase.getInstance().getReference()

        btnInsertData = findViewById(R.id.btnInsertData)

        btnfree = findViewById(R.id.btnfree)

        btnprofile=findViewById(R.id.btnprofile)
        Jobview=findViewById(R.id.Jobview)
        btnI = findViewById(R.id.btnInquiry)





        btnI.setOnClickListener{

            val intent = Intent(this,Fetching_Activity::class.java)
            startActivity(intent)
        }

        btnfree.setOnClickListener{

            val intent = Intent(this,Freelance::class.java)
            startActivity(intent)
        }


        btnInsertData.setOnClickListener{

            val intent = Intent(this,activity_insertion::class.java)
            startActivity(intent)
        }
        btnprofile.setOnClickListener{

            val intent = Intent(this, Seeker_profile::class.java)
            startActivity(intent)

        }

        Jobview.setOnClickListener{

            val intent = Intent(this, job_category::class.java)
            startActivity(intent)

        }




    }




}