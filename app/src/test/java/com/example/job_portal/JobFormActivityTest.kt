@file:Suppress("DEPRECATION")


import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.job_portal.JobForm_Activity
import com.example.job_portal.R
import junit.framework.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class JobFormActivityTest {

    @Test
    fun testUIComponentsAreDisplayed() {
        // Arrange
        val scenario = ActivityScenario.launch(JobForm_Activity::class.java)

        // Act - nothing to do here

        // Assert
        scenario.onActivity { activity ->
            // Check that the UI components are displayed correctly
            assertNotNull(activity.findViewById(R.id.jobname))
            assertNotNull(activity.findViewById(R.id.jobCmpny))
           // assertNotNull(activity.findViewById(R.id.joblocation))
            assertNotNull(activity.findViewById(R.id.timejob))
            assertNotNull(activity.findViewById(R.id.jobName))
            assertNotNull(activity.findViewById(R.id.fllnme))
            assertNotNull(activity.findViewById(R.id.emailtxt))
            assertNotNull(activity.findViewById(R.id.checkBox1))
            assertNotNull(activity.findViewById(R.id.checkBox2))
            assertNotNull(activity.findViewById(R.id.addresstxt))
            assertNotNull(activity.findViewById(R.id.mbiletxt))
            assertNotNull(activity.findViewById(R.id.choosefile))
            assertNotNull(activity.findViewById(R.id.submitbtn))
        }
    }
}
