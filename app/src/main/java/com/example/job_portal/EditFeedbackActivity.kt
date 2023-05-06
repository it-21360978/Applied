package com.example.job_portal

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.job_portal.database.FeedbackDatabaseHelper
import com.example.job_portal.model.UserFeedback
import kotlin.properties.Delegates

class EditFeedbackActivity : AppCompatActivity() {

    private lateinit var feedback: UserFeedback
    private var rating by Delegates.notNull<Int>()
    private lateinit var feedbackEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_feedback)

        val databaseHelper = FeedbackDatabaseHelper(this)
        feedbackEditText = findViewById(R.id.feedback_edit_text)

        feedback = intent.getParcelableExtra("feedback")!!
        rating = feedback.rating
        feedbackEditText.setText(feedback.feedback)
        val star1 : ImageView = findViewById(R.id.star_edit_1)
        val star2 : ImageView = findViewById(R.id.star_edit_2)
        val star3 : ImageView = findViewById(R.id.star_edit_3)
        val star4 : ImageView = findViewById(R.id.star_edit_4)
        val star5 : ImageView = findViewById(R.id.star_edit_5)


        when (feedback.rating) {
            1 -> {
                star1.setImageResource(R.drawable.baseline_star_rate_white)
                star2.setImageResource(R.drawable.baseline_star_rate)
                star3.setImageResource(R.drawable.baseline_star_rate)
                star4.setImageResource(R.drawable.baseline_star_rate)
                star5.setImageResource(R.drawable.baseline_star_rate)
            }
            2 -> {
                star1.setImageResource(R.drawable.baseline_star_rate_white)
                star2.setImageResource(R.drawable.baseline_star_rate_white)
                star3.setImageResource(R.drawable.baseline_star_rate)
                star4.setImageResource(R.drawable.baseline_star_rate)
                star5.setImageResource(R.drawable.baseline_star_rate)
            }
            3 -> {
                star1.setImageResource(R.drawable.baseline_star_rate_white)
                star2.setImageResource(R.drawable.baseline_star_rate_white)
                star3.setImageResource(R.drawable.baseline_star_rate_white)
                star4.setImageResource(R.drawable.baseline_star_rate)
                star5.setImageResource(R.drawable.baseline_star_rate)
            }
            4 -> {
                star1.setImageResource(R.drawable.baseline_star_rate_white)
                star2.setImageResource(R.drawable.baseline_star_rate_white)
                star3.setImageResource(R.drawable.baseline_star_rate_white)
                star4.setImageResource(R.drawable.baseline_star_rate_white)
                star5.setImageResource(R.drawable.baseline_star_rate)
            }
            5 -> {
                star1.setImageResource(R.drawable.baseline_star_rate_white)
                star2.setImageResource(R.drawable.baseline_star_rate_white)
                star3.setImageResource(R.drawable.baseline_star_rate_white)
                star4.setImageResource(R.drawable.baseline_star_rate_white)
                star5.setImageResource(R.drawable.baseline_star_rate_white)
            }
        }

        star1.setOnClickListener {
            star1.setImageResource(R.drawable.baseline_star_rate_white)
            star2.setImageResource(R.drawable.baseline_star_rate)
            star3.setImageResource(R.drawable.baseline_star_rate)
            star4.setImageResource(R.drawable.baseline_star_rate)
            star5.setImageResource(R.drawable.baseline_star_rate)
            rating = 1
        }

        star2.setOnClickListener {
            star1.setImageResource(R.drawable.baseline_star_rate_white)
            star2.setImageResource(R.drawable.baseline_star_rate_white)
            star3.setImageResource(R.drawable.baseline_star_rate)
            star4.setImageResource(R.drawable.baseline_star_rate)
            star5.setImageResource(R.drawable.baseline_star_rate)
            rating = 2

        }

        star3.setOnClickListener {
            star1.setImageResource(R.drawable.baseline_star_rate_white)
            star2.setImageResource(R.drawable.baseline_star_rate_white)
            star3.setImageResource(R.drawable.baseline_star_rate_white)
            star4.setImageResource(R.drawable.baseline_star_rate)
            star5.setImageResource(R.drawable.baseline_star_rate)
            rating = 3

        }

        star4.setOnClickListener {
            star1.setImageResource(R.drawable.baseline_star_rate_white)
            star2.setImageResource(R.drawable.baseline_star_rate_white)
            star3.setImageResource(R.drawable.baseline_star_rate_white)
            star4.setImageResource(R.drawable.baseline_star_rate_white)
            star5.setImageResource(R.drawable.baseline_star_rate)
            rating = 4

        }

        star5.setOnClickListener {
            star1.setImageResource(R.drawable.baseline_star_rate_white)
            star2.setImageResource(R.drawable.baseline_star_rate_white)
            star3.setImageResource(R.drawable.baseline_star_rate_white)
            star4.setImageResource(R.drawable.baseline_star_rate_white)
            star5.setImageResource(R.drawable.baseline_star_rate_white)
            rating = 5

        }
        val saveButton: Button = findViewById(R.id.edit_activity_button)
        saveButton.setOnClickListener {
            val feedbackText = feedbackEditText.text.toString()
            if (feedbackText.isBlank()) {
                Toast.makeText(this, "invalid rating", Toast.LENGTH_SHORT).show()
            } else {
                feedback = UserFeedback(feedback.id, rating, feedbackText)
                databaseHelper.updateFeedback(feedback)
                val resultIntent = Intent()
                resultIntent.putExtra("updatedFeedback", feedback)
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
        }

    }

    companion object {
        const val EDIT_FEEDBACK_REQUEST_CODE = 100
    }
}
