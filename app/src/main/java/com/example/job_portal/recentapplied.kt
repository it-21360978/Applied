package com.example.job_portal

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class recentapplied : AppCompatActivity() {

    private val databaseRef = FirebaseDatabase.getInstance().reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recent_job_list)

        val customerName = "gihan" // replace with the name of the customer you want to retrieve

        val nameTextView: TextView = findViewById(R.id.tvRcompany)
       // val emailTextView: TextView = findViewById(R.id.emailTextView)
       // val phoneTextView: TextView = findViewById(R.id.phoneTextView)

        getCustomerData(customerName) { customer ->
            if (customer != null) {
                nameTextView.text = customer.sfullName
               /* emailTextView.text = customer.email
                phoneTextView.text = customer.phone*/
            } else {
                // handle error
            }
        }
    }

    private fun getCustomerData(customerName: String, onDataReceived: (customer: Customer?) -> Unit) {
        val customerRef = databaseRef.child("jobForms")
            .orderByChild("name")
            .equalTo(customerName)
            .limitToFirst(1)
        customerRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val customer = snapshot.children.first().getValue(Customer::class.java)
                    onDataReceived(customer)
                } else {
                    onDataReceived(null)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                onDataReceived(null)
            }
        })
    }
}
