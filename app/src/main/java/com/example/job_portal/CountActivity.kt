package com.example.job_portal

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class CountActivity : AppCompatActivity() {

    private lateinit var registeredUsersCountRef: DatabaseReference
    private lateinit var registeredUsersCountListener: ValueEventListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_count)

        // Get a reference to the registered users count in the database
        registeredUsersCountRef = FirebaseDatabase.getInstance().reference.child("registeredUsersCount")

        // Set up a listener for changes in the registered users count
        registeredUsersCountListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val registeredUsersCount = snapshot.getValue(Long::class.java)
                updateRegisteredUsersCount(registeredUsersCount)
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle onCancelled event if needed
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // Start listening for changes in the registered users count
        registeredUsersCountRef.addValueEventListener(registeredUsersCountListener)
    }

    override fun onPause() {
        super.onPause()
        // Stop listening for changes in the registered users count
        registeredUsersCountRef.removeEventListener(registeredUsersCountListener)
    }

    private fun updateRegisteredUsersCount(count: Long?) {
        // Update the registered users count on the UI
        val registeredUsersCountTextView = findViewById<TextView>(R.id.registeredUsersCountTextView)
        registeredUsersCountTextView.text = "Registered User Count: ${count.toString()}"
    }
}
