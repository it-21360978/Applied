package com.example.job_portal


import Job
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

import com.example.job_portal.databinding.ActivityJobFormBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference


class JobForm_Activity : AppCompatActivity() {

    // Firebase Realtime Database reference
    private lateinit var databaseRef: DatabaseReference
    private lateinit var storageRef: StorageReference
    private lateinit var binding: ActivityJobFormBinding
    private var fileUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityJobFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseRef = FirebaseDatabase.getInstance().reference
        storageRef = FirebaseStorage.getInstance().reference

        //   binding.submitbtn.setOnClickListener {
        //val intent = Intent(this,ActivityJobViewBinding::class.java)
        //  startActivity(intent)
        // }

        binding.choosefile.setOnClickListener {
            // Create an intent to open the file chooser
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "application/pdf" // Set the MIME type of PDF files
            startActivityForResult(intent, 1)
        }

        // Get the job data from the intent(company adapter eken data gannawa )
        val companyName = intent.getStringExtra("company_name")
        val location = intent.getStringExtra("category")
        val description = intent.getStringExtra("title")
        val age = intent.getStringExtra("type")
     /*   val time = intent.getStringExtra("title")
        val requirements = intent.getStringExtra("description")*/


        // Update the UI with the job details(company adapter eken pass karana data xml eke view karanwa)
        val companyTextView = findViewById<TextView>(R.id.jobname)
        val locationTextView = findViewById<TextView>(R.id.jobCmpny)
        val typeTextView = findViewById<TextView>(R.id.joblocation)
        val descriptionTextView = findViewById<TextView>(R.id.timejob)


        companyTextView.text = companyName
        locationTextView.text = location
        descriptionTextView.text = description
        typeTextView.text=age




        binding.submitbtn.setOnClickListener {
            val jobName= binding.jobName.text.toString()
            val fullName = binding.fllnme.text.toString()
            val email = binding.emailtxt.text.toString()
            val gender = binding.checkBox1.isChecked
            val gender2 = binding.checkBox2.isChecked
            val address = binding.addresstxt.text.toString()
            val mobile = binding.mbiletxt.text.toString()

            if (fullName.isNotEmpty() && email.isNotEmpty() && (gender || gender2) && address.isNotEmpty() && mobile.isNotEmpty()) {

                // Upload the CV file if it has been chosen
                if (fileUri != null) {
                    val fileRef = storageRef.child("jobForms/${fileUri!!.lastPathSegment}")
                    val uploadTask = fileRef.putFile(fileUri!!)
                    uploadTask.addOnSuccessListener {
                        fileRef.downloadUrl.addOnSuccessListener { downloadUri ->
                            val Jobs = Job(
                                fullName,
                                email,
                                gender,
                                gender2,
                                address,
                                mobile,
                                jobName,
                                downloadUri.toString(),

                            )

                            val jobFormRef = databaseRef.child("jobForms").push()
                            jobFormRef.setValue(Jobs).addOnSuccessListener {
                                // Show success message
                                Toast.makeText(
                                    this,
                                    "Data inserted successfully",
                                    Toast.LENGTH_SHORT
                                ).show()

                                // Clear the form fields
                                binding.fllnme.setText("")
                                binding.emailtxt.setText("")
                                binding.checkBox1.isChecked = false
                                binding.checkBox2.isChecked = false
                                binding.addresstxt.setText("")
                                binding.mbiletxt.setText("")
                                binding.jobName.setText("")
                                fileUri = null

                            }.addOnFailureListener {
                                // Show failure message
                                Toast.makeText(this, "Data insertion failed", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    }.addOnFailureListener {
                        // Show failure message
                        Toast.makeText(this, "File upload failed", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // If no CV file has been chosen, create a Job object without the download URL
                    val Jobs = Job(fullName,email,gender, gender2, address, mobile, jobName)

                    val jobFormRef = databaseRef.child("jobForms").push()
                    jobFormRef.setValue(Jobs).addOnSuccessListener {
                        // Show success message
                        Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_SHORT)
                            .show()
                        // Clear the form fields
                        binding.fllnme.setText("")
                        binding.emailtxt.setText("")
                        binding.checkBox1.isChecked = false
                        binding.checkBox2.isChecked = false
                        binding.addresstxt.setText("")
                        binding.mbiletxt.setText("")
                        binding.jobName.setText("")
                        fileUri = null

                        val intent = Intent(this,card_view::class.java)
                        // Start the new activity
                        startActivity(intent)

                    }
                }
            }
        }
    }
}











