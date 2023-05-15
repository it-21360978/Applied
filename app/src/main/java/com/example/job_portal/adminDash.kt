package com.example.job_portal
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView

class adminDash : AppCompatActivity() {


    private lateinit var btnJ: CardView
    private lateinit var btnInq: CardView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_dash)


        btnJ = findViewById(R.id.job)
        btnInq = findViewById(R.id.btnInq)



        btnJ.setOnClickListener{

            val intent = Intent(this,CountActivity::class.java)
            startActivity(intent)

        }

        btnInq.setOnClickListener{

            val intent = Intent(this,adminInqView::class.java)
            startActivity(intent)
        }
    }
}