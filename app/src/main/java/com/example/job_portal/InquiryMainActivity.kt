package com.example.job_portal



import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class InquiryMainActivity : AppCompatActivity() {

    private lateinit var btnInsertData: CardView

    private lateinit var btnfree: CardView
    private lateinit var btnskill: CardView
    private lateinit var btnprofile: CardView







    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)
        supportActionBar?.hide()

        val firebase :DatabaseReference = FirebaseDatabase.getInstance().getReference()

        btnInsertData = findViewById(R.id.btnInsertData)

        btnfree = findViewById(R.id.btnfree)
        btnskill = findViewById(R.id.btnskill)
        btnprofile=findViewById(R.id.btnprofile)
   





        btnskill.setOnClickListener{

            val intent = Intent(this,interview_1::class.java)
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


    }
}