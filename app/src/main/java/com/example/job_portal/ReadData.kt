package com.example.job_portal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

private fun Intent.putExtra(s: String, job: Job?) {

}

class ReadData : AppCompatActivity() {
    private lateinit var etusername: EditText
    private lateinit var readdataBtn: Button
    private lateinit var databaseRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.retrieveapplication)

        etusername = findViewById(R.id.etusername)
        readdataBtn = findViewById(R.id.readdataBtn)
        databaseRef = FirebaseDatabase.getInstance().getReference()

        readdataBtn.setOnClickListener {
            val fullName = etusername.text.toString()
            val query = databaseRef.child("jobForms").orderByChild("jobName").equalTo(fullName)
            query.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val job = snapshot.children.first().getValue(Job::class.java)
                        // Pass the job object to the details page and start the new activity
                        val intent = Intent(this@ReadData, jobeditDelete::class.java)
                        intent.putExtra("job", job)
                        startActivity(intent)
                    } else {
                        // Show a message indicating that no job form was found with the provided full name
                        Toast.makeText(
                            this@ReadData,
                            "No job form found with the provided full name",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Show an error message
                    Toast.makeText(this@ReadData, "Failed to retrieve job form", Toast.LENGTH_SHORT)
                        .show()
                }
            })
        }
    }
}
