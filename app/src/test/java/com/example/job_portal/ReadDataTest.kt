package com.example.job_portal

import Job
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ReadDataTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(ReadData::class.java)

    @Test
    fun testReadData() {
        // Create a test intent with a valid job object
        val job = Job(
            "John Doe",
            "johndoe@example.com",
            true,
            false,
            "123 Main St",
            "555-555-5555",
            "Software Engineer",
            "http://example.com/johndoe.pdf"
        )
        val intent = Intent(ApplicationProvider.getApplicationContext(), jobeditDelete::class.java)
            .putExtra("job", job)

        // Launch the activity with the test intent
        activityScenarioRule.scenario.onActivity { activity ->
            activity.startActivity(intent)
        }

        // Verify that the activity displays the correct job information
        activityScenarioRule.scenario.onActivity { activity ->
            val fullNameEditText = activity.findViewById<EditText>(R.id.etusername)
            val readDataButton = activity.findViewById<Button>(R.id.readdataBtn)

            assertEquals("John Doe", fullNameEditText.text.toString())
            assertEquals("Software Engineer", readDataButton.text.toString())
        }
    }

    @Test
    fun testReadDataNotFound() {
        // Create a test intent with an invalid full name
        val intent = Intent(ApplicationProvider.getApplicationContext(), jobeditDelete::class.java)
            .putExtra("fullName", "Jane Doe")

        // Launch the activity with the test intent
        activityScenarioRule.scenario.onActivity { activity ->
            activity.startActivity(intent)
        }

        // Verify that the activity displays the "no job form found" message
        activityScenarioRule.scenario.onActivity { activity ->
            val fullNameEditText = activity.findViewById<EditText>(R.id.etusername)
            val readDataButton = activity.findViewById<Button>(R.id.readdataBtn)

            assertEquals("", fullNameEditText.text.toString())

        }
    }
}

private fun ReadData.startActivity(intent: Unit) {

}
