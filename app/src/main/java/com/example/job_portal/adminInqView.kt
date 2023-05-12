package com.example.job_portal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*


class adminInqView : AppCompatActivity(){

    private lateinit var InqRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var List: ArrayList<InquiryModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetching)
        supportActionBar?.hide()

        InqRecyclerView = findViewById(R.id.rvEmp)
        InqRecyclerView.layoutManager = LinearLayoutManager(this)
        InqRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        List = arrayListOf<InquiryModel>()

        getData()

    }

    private fun getData() {

        InqRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Inquiries")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                List.clear()
                if (snapshot.exists()){
                    for (empSnap in snapshot.children){
                        val empData = empSnap.getValue(InquiryModel::class.java)
                        List.add(empData!!)
                    }
                    val mAdapter = EmpAdapter(List)
                    InqRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : EmpAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {

                            val intent = Intent(this@adminInqView, InquiryDetailsActivity::class.java)

                            //put extras
                            intent.putExtra("Id", List[position].Id)
                            intent.putExtra("Name", List[position].Name)
                            intent.putExtra("Email", List[position].Email)
                            intent.putExtra("Inq", List[position].Inq)
                            startActivity(intent)
                        }

                    })

                    InqRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}