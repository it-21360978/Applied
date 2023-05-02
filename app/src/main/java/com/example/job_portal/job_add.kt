package com.example.job_portal

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import com.example.job_portal.databinding.ActivityJobAddBinding
import com.example.job_portal.databinding.ActivityJobFormBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class job_add : AppCompatActivity() {

    // Firebase Realtime Database reference
    private lateinit var databaseRef: DatabaseReference
    private lateinit var binding: ActivityJobAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityJobAddBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.postbutton.setOnClickListener {
            val companyName = binding.companyName.text.toString()
            val title=binding.title.text.toString()
            val category=binding.category.text.toString()
            val type=binding.type.text.toString()
            val salary=binding.salary.text.toString()
            val description=binding.description.text.toString()

            databaseRef=FirebaseDatabase.getInstance().getReference("Jobs")
            val company=CompanyData(companyName, title, category, type, salary, description)

            databaseRef.child(companyName).setValue(company).addOnSuccessListener {
                binding.companyName.text.clear()
                binding.title.text.clear()
                binding.category.text.clear()
                binding.type.text.clear()
                binding.salary.text.clear()
                binding.description.text.clear()


                Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show()



            }
        }
    }
}
