package com.example.job_portal

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.job_portal.databinding.ActivityJobAddBinding
import com.example.job_portal.databinding.ActivityJobFormBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

/*class job_add : AppCompatActivity() {

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


                Toast.makeText(this, "Submit Successfully", Toast.LENGTH_SHORT).show()



            }
        }
    }
}*/

class job_add : AppCompatActivity() {

    private lateinit var companyname: EditText
    private lateinit var title: EditText
    private lateinit var category: EditText
    private lateinit var type:EditText
    private lateinit var salary: EditText
    private lateinit var description: EditText
    private lateinit var postbutton: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_add)

        companyname = findViewById(R.id.companyname)
        title = findViewById(R.id.title)
        category = findViewById(R.id.category)
        type = findViewById(R.id.type)
        salary= findViewById(R.id.salary)
        description = findViewById(R.id.description)
        postbutton = findViewById(R.id.postbutton)

        dbRef = FirebaseDatabase.getInstance().getReference("Jobs")

        postbutton.setOnClickListener {
            saveCompanyData()
        }
    }

    private fun saveCompanyData() {

        //getting values
        val Ccompanyname =companyname.text.toString()
        val Ctitle  =  title.text.toString()
        val Ccategory  =  category.text.toString()
        val Ctype =  type.text.toString()
        val  Csalary =    salary.text.toString()
        val  Cdescription =  description.text.toString()


        if (Ccompanyname.isEmpty()) {
            companyname.error = "Please enter company name"
        }
        if ( Ctitle.isEmpty()) {
            title.error = "Please enter title"
        }
        if (Ccategory.isEmpty()) {
            category.error = "Please enter category"
        }
        if (Ctype.isEmpty()) {
            type.error = "Please enter type"
        }
        if (Csalary.isEmpty()) {
            salary.error = "Please enter salary"
        }
        if (Cdescription.isEmpty()) {
            description.error = "Please enter description"
        }


        //check if any of the fields are empty
        if (Ccompanyname.isEmpty() || Ctitle.isEmpty() || Ccategory.isEmpty() || Ctype.isEmpty() || Csalary.isEmpty() || Cdescription.isEmpty()) {
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_LONG).show()
            return
        }

        val CcomId = dbRef.push().key!!

        val company = CompanyData(CcomId,Ccompanyname, Ctitle, Ccategory, Ctype,Csalary,Cdescription)

        dbRef.child(CcomId).setValue(company)
            .addOnCompleteListener {
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()

                companyname.text.clear()
                title.text.clear()
                category.text.clear()
                type.text.clear()
                salary.text.clear()
                description.text.clear()


            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }

    }
    override fun onBackPressed() {
        val intent = Intent(this, company_dash::class.java)
        startActivity(intent)
        finish()
    }

}

