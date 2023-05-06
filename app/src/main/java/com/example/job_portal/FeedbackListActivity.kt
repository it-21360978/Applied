package com.example.job_portal
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.job_portal.EditFeedbackActivity.Companion.EDIT_FEEDBACK_REQUEST_CODE
import com.example.job_portal.adapter.FeedbackListAdapter
import com.example.job_portal.database.FeedbackDatabaseHelper
import com.example.job_portal.model.UserFeedback

class FeedbackListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FeedbackListAdapter
    private lateinit var feedbackList: MutableList<UserFeedback>
    private lateinit var feedbackDatabaseHelper: FeedbackDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback_list)

        feedbackList = mutableListOf() // Initialize the property

        recyclerView = findViewById(R.id.feedback_recycler_view)
        adapter = FeedbackListAdapter(feedbackList, this::onEditClick, this::onDeleteClick)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        feedbackDatabaseHelper = FeedbackDatabaseHelper(this)
        feedbackDatabaseHelper.getAllFeedback { feedbackList ->
            this.feedbackList.clear() // Clear the list before adding new data
            this.feedbackList.addAll(feedbackList)
            adapter.notifyDataSetChanged() // Notify the adapter of changes
        }
    }

    private fun onEditClick(feedback: UserFeedback) {
        val intent = Intent(this, EditFeedbackActivity::class.java)
        intent.putExtra("feedback", feedback)
        startActivityForResult(intent, EDIT_FEEDBACK_REQUEST_CODE)
    }

    private fun onDeleteClick(feedback: UserFeedback) {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Confirm Delete")
        alertDialog.setMessage("Are you sure you want to delete this feedback?")
        alertDialog.setPositiveButton("Yes") { _, _ ->
            feedbackDatabaseHelper.deleteFeedback(feedback)
            feedbackList.remove(feedback)
            adapter.notifyDataSetChanged()
        }
        alertDialog.setNegativeButton("No", null)
        alertDialog.show()
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == EDIT_FEEDBACK_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val updatedFeedback : UserFeedback = data?.getParcelableExtra("updatedFeedback")!!
            val index = feedbackList.indexOfFirst { it.id == updatedFeedback.id }
            if (index != -1) {
                feedbackList[index] = updatedFeedback
                adapter.notifyDataSetChanged() // Update RecyclerView
            }


        }
    }
}