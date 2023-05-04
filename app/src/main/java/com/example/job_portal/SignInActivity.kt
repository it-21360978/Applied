package com.example.job_portal


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import com.example.job_portal.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth
import job_list

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.textView.setOnClickListener{
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.button.setOnClickListener{
            val email = binding.emailEt.text.toString()
            val pass = binding.passET.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty()) {
                val selectedRadioButtonId = binding.radioGroup.checkedRadioButtonId
                if (selectedRadioButtonId == -1) {
                    Toast.makeText(this, "Please select a user type", Toast.LENGTH_LONG).show()
                } else {
                    val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId)
                    val userType = selectedRadioButton.text.toString()

                    when (userType) {

                        "Company" -> {
                            firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                                if (it.isSuccessful) {
                                    val intent = Intent(this, company_dash::class.java)
                                    startActivity(intent)
                                } else {
                                    Toast.makeText(this, it.exception.toString(), Toast.LENGTH_LONG).show()
                                }
                            }
                        }
                        "Seeker" -> {
                            firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                                if (it.isSuccessful) {
                                    val intent = Intent(this, Seeker_profile::class.java)
                                    startActivity(intent)
                                } else {
                                    Toast.makeText(this, it.exception.toString(), Toast.LENGTH_LONG).show()
                                }
                            }
                        }

                        "Admin" -> {
                            firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                                if (it.isSuccessful) {
                                    val intent = Intent(this, adminDash::class.java)
                                    startActivity(intent)
                                } else {
                                    Toast.makeText(this, it.exception.toString(), Toast.LENGTH_LONG).show()
                                }
                            }
                        }
                    }
                }
            } else {
                Toast.makeText(this,"Empty fields are not allowed !!!", Toast.LENGTH_LONG).show()
            }
        }
    }
}
