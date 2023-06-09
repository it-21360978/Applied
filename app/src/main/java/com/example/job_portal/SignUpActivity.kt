package com.example.job_portal
//Import necessary dependencies
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.job_portal.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class SignUpActivity : AppCompatActivity() {
    //Declare variables and references
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var registeredUsersRef: DatabaseReference
    private var totalRegisteredUsers = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//Set the activity's layout using ViewBinding
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
//Initialize Firebase Authentication and the database reference:
        firebaseAuth = FirebaseAuth.getInstance()
        registeredUsersRef = FirebaseDatabase.getInstance().reference.child("registeredUsersCount")
//Read the current count of registered users from the database
        registeredUsersRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    totalRegisteredUsers = dataSnapshot.getValue(Int::class.java) ?: 0
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle database error
            }
        })
//Set a click listener for the sign-up button
        binding.button.setOnClickListener {

            // Retrieve user input (email, password, etc.)
            val email = binding.emailEt.text.toString()
            val pass = binding.passET.text.toString()
            val confirmPass = binding.confirmPassEt.text.toString()

            val selectedOption = binding.radioGroup.checkedRadioButtonId
            val userType: String = if (selectedOption == R.id.radioSeeker) {
                "Seeker"
            } else {
                "Company"
            }
// Validate input fields
            if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
                if (pass == confirmPass) {
                    // Create user account using Firebase Authentication
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            totalRegisteredUsers++

                            val intent = Intent(this@SignUpActivity, SignInActivity::class.java)

                            // Store the updated count in the database
                            registeredUsersRef.setValue(totalRegisteredUsers)
                        } else {
                            Toast.makeText(this, task.exception.toString(), Toast.LENGTH_LONG).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Password is not matching", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "Empty fields are not allowed !!!", Toast.LENGTH_LONG).show()
            }
        }
    }
}