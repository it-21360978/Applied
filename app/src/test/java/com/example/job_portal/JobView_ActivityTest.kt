package com.example.job_portal
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class JobView_ActivityTest {

    @Test
    fun `test getJobData`() {
        val activity = JobView_Activity()
        activity.getJobData()
        assertTrue(activity.jobArrayList.size > 0)
    }
}
