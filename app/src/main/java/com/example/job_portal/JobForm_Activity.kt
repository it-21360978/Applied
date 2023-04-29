package com.example.job_portal


import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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




        binding.submitbtn.setOnClickListener {
            val sFullName = binding.fllnme.text.toString()
            val sEmail = binding.emailtxt.text.toString()
            val sGender = binding.checkBox1.isChecked
            val sGender2 = binding.checkBox2.isChecked
            val sAddress = binding.addresstxt.text.toString()
            val sMobile = binding.mbiletxt.text.toString()

            if (sFullName.isNotEmpty() && sEmail.isNotEmpty() && (sGender || sGender2) && sAddress.isNotEmpty() && sMobile.isNotEmpty()) {

                // Upload the CV file if it has been chosen
                if (fileUri != null) {
                    val fileRef = storageRef.child("jobForms/${fileUri!!.lastPathSegment}")
                    val uploadTask = fileRef.putFile(fileUri!!)
                    uploadTask.addOnSuccessListener {
                        fileRef.downloadUrl.addOnSuccessListener { downloadUri ->
                            val Jobs = Job(
                                sFullName,
                                sEmail,
                                sGender,
                                sGender2,
                                sAddress,
                                sMobile,
                                downloadUri.toString()
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
                    val Jobs = Job(sFullName, sEmail, sGender, sGender2, sAddress, sMobile)

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
                        fileUri = null
                    }
                }
            }
        }
    }
}











