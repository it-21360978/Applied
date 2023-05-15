package com.example.job_portal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.FirebaseDatabase

class update_delete_view : AppCompatActivity() {

     private lateinit var comId: TextView
     private lateinit var companyname: TextView
     private lateinit var title: TextView
     private lateinit var type: TextView
     private lateinit var category: TextView
     private lateinit var salary: TextView
     private lateinit var description: TextView
     private lateinit var btnUpdate: Button
     private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_delete_view)

        initView()
        setValuesToViews()

        btnUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("CcomId").toString(),
                intent.getStringExtra("CcompanyName").toString(),
            )
        }

        btnDelete.setOnClickListener {
              deleteRecord(
              intent.getStringExtra("CcomId").toString()
        )
    }

}


     private fun initView() {
        comId = findViewById(R.id.comId)
        companyname = findViewById(R.id.companyname)
        type = findViewById(R.id.type)
        category = findViewById(R.id.category)
        salary = findViewById(R.id. salary)
        title = findViewById(R.id.title)
        description = findViewById(R.id.description)


        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setValuesToViews() {
        comId.text = intent.getStringExtra("CcomId")
        companyname.text = intent.getStringExtra("CcompanyName")
        type.text = intent.getStringExtra("Ctype")
        category.text = intent.getStringExtra("Ccategory")
        salary.text = intent.getStringExtra("Csalary")
        title.text = intent.getStringExtra("Ctitle")
        description.text = intent.getStringExtra("Cdescription")

    }

     private fun openUpdateDialog(
        CcomId: String,
        CcompanyName: String

    ) {
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.activity_edit_jobs, null)

        mDialog.setView(mDialogView)

        val ecompanyname = mDialogView.findViewById<EditText>(R.id.ecompanyname)
        val etitle = mDialogView.findViewById<EditText>(R.id.etitle)
        val ecategory = mDialogView.findViewById<EditText>(R.id.ecategory)
        val etype = mDialogView.findViewById<EditText>(R.id.etype)
        val esalary= mDialogView.findViewById<EditText>(R.id.esalary)
        val edescription= mDialogView.findViewById<EditText>(R.id.edescription)
        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        ecompanyname.setText(intent.getStringExtra("CcompanyName").toString())
        etitle.setText(intent.getStringExtra("Ctitle").toString())
        ecategory.setText(intent.getStringExtra("Ccategory").toString())
        etype.setText(intent.getStringExtra("Ctype").toString())
        esalary.setText(intent.getStringExtra("Csalary").toString())
        edescription.setText(intent.getStringExtra("Cdescription").toString())

        mDialog.setTitle("Updating $CcompanyName Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener {
            updateComData(
                CcomId,
                ecompanyname.text.toString(),
                etitle.text.toString(),
                ecategory.text.toString(),
                etype.text.toString(),
                esalary.text.toString(),
                edescription.text.toString()

            )

            Toast.makeText(applicationContext, "Employee Data Updated", Toast.LENGTH_LONG).show()

            //we are setting updated data to our textviews
            companyname.text = ecompanyname.text.toString()
            title.text = etitle.text.toString()
            category.text = ecategory.text.toString()
            type.text=etype.text.toString()
            salary.text=esalary.text.toString()
            description.text=edescription.text.toString()


            alertDialog.dismiss()
        }
    }

    private  fun updateComData(

        Id: String,
        Name: String,
        Title: String,
        Category: String,
        Salary:String,
        Type: String,
        Description:String,
    ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Jobs").child(Id)
        val comInfo = CompanyData(Id, Name, Title, Category, Salary,Type,Description)
        dbRef.setValue(comInfo)
    }

  private fun deleteRecord(
        Id: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Jobs").child(Id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Employee data deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this, post_jobs::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error ->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

}

