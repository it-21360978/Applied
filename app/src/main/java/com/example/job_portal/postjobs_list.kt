package com.example.job_portal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class postjobs_list : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_postjobs_list)

    }
}


  /*  private lateinit var jobRecyclerView: RecyclerView
    private lateinit var companyname: TextView
    private lateinit var comList: ArrayList<CompanyData>
    private lateinit var dbRef: DatabaseReference


override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_postjobs_list)

    jobRecyclerView = findViewById(R.id.rvJob)
    jobRecyclerView.layoutManager = LinearLayoutManager(this)
    jobRecyclerView.setHasFixedSize(true)
    companyname = findViewById(R.id.companyname)
    comList = arrayListOf<CompanyData>()

    getJobData()
    setValues()
}

private fun getJobData(){

    jobRecyclerView.visibility = View.GONE
    companyname.visibility = View.VISIBLE

    dbRef = FirebaseDatabase.getInstance().getReference("Jobs")

    dbRef.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            comList.clear()
            if (snapshot.exists()){
                for (comSnap in snapshot.children){
                    val comData = comSnap.getValue(CompanyData::class.java)
                    comList.add(comData!!)
                }
                val mAdapter = jobsAdapter(comList)
                jobRecyclerView.adapter = mAdapter

                jobRecyclerView.visibility = View.VISIBLE
                companyname.visibility = View.GONE
            }
        }

        override fun onCancelled(error: DatabaseError) {
            TODO("Not yet implemented")
        }

    })
}

private fun setValues(){
    companyname.text=intent.getStringExtra("CcompanyName")

}
}*/
