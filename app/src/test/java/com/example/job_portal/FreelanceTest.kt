import android.content.Intent
import androidx.test.core.app.ActivityScenario
import com.example.job_portal.Freelance
import com.example.job_portal.free_page1
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FreelanceTest {

    private lateinit var activityScenario: ActivityScenario<Freelance>

    @Before
    fun setup() {
        activityScenario = ActivityScenario.launch(Freelance::class.java)
    }

    @Test
    fun testButtonClick_StartsNextActivity() {
        activityScenario.onActivity { activity ->
            // Perform button click
            activity.btnGetstart.performClick()

            // Verify that the expected activity is launched
            val expectedIntent = Intent(activity, free_page1::class.java)
            val startedIntent = activityScenario.result?.resultData
            assertEquals(expectedIntent.component?.className, startedIntent?.component?.className)
        }
    }
}
