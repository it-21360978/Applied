package com.example.job_portal.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.job_portal.R
import com.example.job_portal.model.UserFeedback

class FeedbackListAdapter(
    private val feedbacks: List<UserFeedback>,
    private val onEditClick: (UserFeedback) -> Unit,
    private val onDeleteClick: (UserFeedback) -> Unit
) : RecyclerView.Adapter<FeedbackListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val feedbackTextView: TextView = view.findViewById(R.id.feedback_text_view)
        val editButton: Button = view.findViewById(R.id.edit_button)
        val deleteButton: Button = view.findViewById(R.id.delete_button)
        val star1 : ImageView =  view.findViewById(R.id.star_item_1)
        val star2 : ImageView =  view.findViewById(R.id.star_item_2)
        val star3 : ImageView =  view.findViewById(R.id.star_item_3)
        val star4 : ImageView =  view.findViewById(R.id.star_item_4)
        val star5 : ImageView =  view.findViewById(R.id.star_item_5)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_feedback, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val feedback = feedbacks[position]
        holder.feedbackTextView.text = feedback.feedback
        holder.editButton.setOnClickListener { onEditClick(feedback) }
        holder.deleteButton.setOnClickListener { onDeleteClick(feedback) }

        when (feedback.rating) {
            1 -> {
                holder.star1.setImageResource(R.drawable.baseline_star_rate_white)
                holder.star2.setImageResource(R.drawable.baseline_star_rate)
                holder.star3.setImageResource(R.drawable.baseline_star_rate)
                holder.star4.setImageResource(R.drawable.baseline_star_rate)
                holder.star5.setImageResource(R.drawable.baseline_star_rate)
            }
            2 -> {
                holder.star1.setImageResource(R.drawable.baseline_star_rate_white)
                holder.star2.setImageResource(R.drawable.baseline_star_rate_white)
                holder.star3.setImageResource(R.drawable.baseline_star_rate)
                holder.star4.setImageResource(R.drawable.baseline_star_rate)
                holder.star5.setImageResource(R.drawable.baseline_star_rate)
            }
            3 -> {
                holder.star1.setImageResource(R.drawable.baseline_star_rate_white)
                holder.star2.setImageResource(R.drawable.baseline_star_rate_white)
                holder.star3.setImageResource(R.drawable.baseline_star_rate_white)
                holder.star4.setImageResource(R.drawable.baseline_star_rate)
                holder.star5.setImageResource(R.drawable.baseline_star_rate)
            }
            4 -> {
                holder.star1.setImageResource(R.drawable.baseline_star_rate_white)
                holder.star2.setImageResource(R.drawable.baseline_star_rate_white)
                holder.star3.setImageResource(R.drawable.baseline_star_rate_white)
                holder.star4.setImageResource(R.drawable.baseline_star_rate_white)
                holder.star5.setImageResource(R.drawable.baseline_star_rate)
            }
            5 -> {
                holder.star1.setImageResource(R.drawable.baseline_star_rate_white)
                holder.star2.setImageResource(R.drawable.baseline_star_rate_white)
                holder.star3.setImageResource(R.drawable.baseline_star_rate_white)
                holder.star4.setImageResource(R.drawable.baseline_star_rate_white)
                holder.star5.setImageResource(R.drawable.baseline_star_rate_white)
            }
        }
    }

    override fun getItemCount(): Int = feedbacks.size
}
