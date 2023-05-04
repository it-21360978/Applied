package com.example.job_portal



import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.job_portal.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Initialize the binding variable inside the onCreate method.
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.textView.setOnClickListener{
            val intent = Intent(this,SignInActivity::class.java)
            startActivity(intent)
        }
        binding.button.setOnClickListener{
// Initialize the views
            val email =binding.emailEt.text.toString()
            val pass = binding.passET.text.toString()
            val confirmPass = binding.confirmPassEt.text.toString()

            // Retrieve the selected RadioButton option
            val selectedOption = binding.radioGroup.checkedRadioButtonId
            val userType: String = if (selectedOption == R.id.radioSeeker) {
                "Seeker"
            } else {
                "Company"
            }

            if(email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()){

                if(pass == confirmPass){

                    firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener{

                        if(it.isSuccessful){



                            // Create different user accounts based on the user's selection
                            val userId = firebaseAuth.currentUser?.uid
                            val database = FirebaseDatabase.getInstance().getReference("Users").child(userId!!)
                            database.child("userType").setValue(userType)

                            if (userType == "Seeker") {
                                // Navigate to Seeker dashboard
                                val intent = Intent(this, SignInActivity::class.java)
                                startActivity(intent)
                            } else {
                                // Navigate to Company dashboard
                                val intent = Intent(this, SignInActivity::class.java)
                                startActivity(intent)
                            }

                        }else{
                            Toast.makeText(this,it.exception.toString(),Toast.LENGTH_LONG).show()
                        }
                    }

                }else{

                    Toast.makeText(this,"Password is not matching",Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this,"Empty fields are not allowed !!!",Toast.LENGTH_LONG).show()
            }

        }

    }
}



