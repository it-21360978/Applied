package com.example.job_portal


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class activity_insertion : AppCompatActivity(){

    //initialize views
    private lateinit var username: EditText
    private lateinit var email: EditText
    private lateinit var Msg: EditText
    private lateinit var btnSaveData: Button

    //create db reference
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertion)
        supportActionBar?.hide()

        username = findViewById(R.id.Name)
        email = findViewById(R.id.Email)
        Msg = findViewById(R.id.Msg)
        btnSaveData = findViewById(R.id.btnSave)

        dbRef = FirebaseDatabase.getInstance().getReference("Inquiries")

        //save data
        btnSaveData.setOnClickListener {
            saveData()
        }
    }

    private fun saveData() {

        //getting values
        val Name = username.text.toString()
        val Email = email.text.toString()
        val Inq = Msg.text.toString()

        //form validations
        if (Name.isEmpty()) {
            username.error = "Please enter name"
        }
        if (Email.isEmpty()) {
            email.error = "Please enter email"
        }
        if (Inq.isEmpty()) {
            Msg.error = "Please enter a message"
        }

        val Id = dbRef.push().key!!

        val inquiry = InquiryModel(Id,Name,Email,Inq)

//put data in firebase database
        dbRef.child(Id).setValue(inquiry)
            .addOnCompleteListener {
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()

                username.text.clear()
                email.text.clear()
                Msg.text.clear()


            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }

    }

}






