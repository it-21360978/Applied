package com.example.job_portal


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.FirebaseDatabase

class InquiryDetailsActivity : AppCompatActivity() {

    private lateinit var Id: TextView
    private lateinit var Name: TextView
    private lateinit var Email: TextView
    private lateinit var Inq: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_details)
        supportActionBar?.hide()

        initView()
        setValuesToViews()



        btnUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("Id").toString(),
                intent.getStringExtra("Name").toString()
            )
        }

        btnDelete.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("Id").toString()
            )
        }

    }

    private fun initView() {
        Id = findViewById(R.id.tvEmpId)
        Name = findViewById(R.id.tvEmpName)
        Email = findViewById(R.id.tvEmpAge)
        Inq = findViewById(R.id.tvEmpSalary)

        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setValuesToViews() {
        Id.text = intent.getStringExtra("Id")
        Name.text = intent.getStringExtra("Name")
        Email.text = intent.getStringExtra("Email")
        Inq.text = intent.getStringExtra("Inq")

    }

    private fun deleteRecord(
        id: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Inquiries").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Employee data deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this, Fetching_Activity::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error ->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun openUpdateDialog(
        Id: String,
        empName: String,

        ) {
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_dialog, null)

        mDialog.setView(mDialogView)

        val eName = mDialogView.findViewById<EditText>(R.id.etEmpName)
        val eEmail = mDialogView.findViewById<EditText>(R.id.etEmpAge)
        val eMsg = mDialogView.findViewById<EditText>(R.id.etEmpSalary)

        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        eName.setText(intent.getStringExtra("Name").toString())
        eEmail.setText(intent.getStringExtra("Email").toString())
        eMsg.setText(intent.getStringExtra("Inq").toString())

        mDialog.setTitle("Updating $empName Record")

        val alertDialog = mDialog.create()
        alertDialog.show()


        btnUpdateData.setOnClickListener {
            updateEmpData(
                Id,
                eName.text.toString(),
                eEmail.text.toString(),
                eMsg.text.toString()
            )

            Toast.makeText(applicationContext, "Employee Data Updated", Toast.LENGTH_LONG).show()

            //we are setting updated data to our textviews
            Name.text = eName.text.toString()
            Email.text = eEmail.text.toString()
            Inq.text = eMsg.text.toString()

            alertDialog.dismiss()
        }


    }


    private fun updateEmpData(
        id: String,
        name: String,
        email: String,
        msg: String
    ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Inquiries").child(id)
        val empInfo = InquiryModel(id, name, email, msg)
        dbRef.setValue(empInfo)


        val home = findViewById<ImageButton>(R.id.vhome)
        home.setOnClickListener {
            val intent = Intent(this,InquiryDetailsActivity::class.java)
            startActivity(intent)
        }









    }







}