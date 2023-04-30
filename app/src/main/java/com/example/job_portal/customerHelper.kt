package com.example.job_portal

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class customerHelper {
    private val databaseRef = FirebaseDatabase.getInstance().reference

    // function to get customer data using their name as parameter
    fun getCustomerDataByName(name: String, onDataReceived: (customer: Customer?) -> Unit) {
        // create a query to search for the customer with the given name
        val query = databaseRef.child("jobForms").orderByChild("name").equalTo(name)
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    // get the first result from the snapshot
                    val customerSnapshot = snapshot.children.first()
                    // convert the snapshot to a Customer object
                    val customer = customerSnapshot.getValue(Customer::class.java)
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