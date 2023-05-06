package com.example.job_portal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class recieve_job_application : AppCompatActivity() {

   private lateinit var SekRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var SekList: ArrayList<Customer>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recieve_job_application)

       SekRecyclerView = findViewById(R.id.rvSeeker)
        SekRecyclerView.layoutManager = LinearLayoutManager(this)
        SekRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        SekList = arrayListOf<Customer>()

        getSekData()
    }

    private fun getSekData(){

        SekRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Job_Forms")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                SekList.clear()
                if (snapshot.exists()){
                    for (SekSnap in snapshot.children){
                        val SekData = SekSnap.getValue(Customer::class.java)
                        SekList.add(SekData!!)
                    }
                    val mAdapter = SeekerAdapter(SekList)
                    SekRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : SeekerAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {

                            val intent = Intent(this@recieve_job_application, seeker_details::class.java)

                            //put extras
                          /*  intent.putExtra("sfullName", SekList[position].sfullName)
                            intent.putExtra("jobName", SekList[position].jobName)
                            intent.putExtra("semail", SekList[position].semail)
                            intent.putExtra("saddress", SekList[position].saddress)
                            intent.putExtra("smobile", SekList[position].smobile)
                            intent.putExtra("cvUrl", SekList[position].cvUrl)
                            startActivity(intent)*/
                        }

                    })

                   SekRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
    override fun onBackPressed() {
        val intent = Intent(this, company_dash::class.java)
        startActivity(intent)
        finish()
    }
}