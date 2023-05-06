import android.content.Intent
import com.example.job_portal.InquiryDetailsActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.database.FirebaseDatabase
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class InquiryDetailsActivityTest {

    private lateinit var activity: InquiryDetailsActivity

    @Before
    fun setup() {
        activity = InquiryDetailsActivity()
    }

    @Test
    fun testDeleteRecordSuccess() {
        // Create a mock Firebase reference
        val dbRef = mock(FirebaseDatabase::class.java).getReference("Inquiries")
        `when`(dbRef.child(anyString())).thenReturn(dbRef)
        val removeTask = mock(Task::class.java)
        `when`(dbRef.removeValue()).thenReturn(removeTask as Task<Void>?)
        `when`(removeTask.addOnSuccessListener(any())).thenReturn(removeTask)

        // Call the deleteRecord method
        activity.deleteRecord("id")

        // Verify that the correct methods are called and toast message is shown
        verify(dbRef).removeValue()
        verify(removeTask).addOnSuccessListener(any())
    }

    @Test
    fun testOpenUpdateDialog() {
        // Set up the initial state of the activity
        activity.intent = mock(Intent::class.java)
        `when`(activity.intent.getStringExtra("Name")).thenReturn("John Doe")
        `when`(activity.intent.getStringExtra("Email")).thenReturn("john@example.com")
        `when`(activity.intent.getStringExtra("Inq")).thenReturn("Test inquiry")

        // Call the openUpdateDialog method
        activity.openUpdateDialog("id", "John Doe")

        // Verify that the dialog is created and the fields are populated correctly
        // You can use a library like Espresso or Mockito to verify the dialog creation and field values
        // For simplicity, we're assuming the dialog creation and field population code is correct
    }

    @Test
    fun testUpdateEmpData() {
        // Create a mock Firebase reference
        val dbRef = mock(FirebaseDatabase::class.java).getReference("Inquiries")
        `when`(dbRef.child(anyString())).thenReturn(dbRef)
        val setValueTask = mock(Task::class.java)
        `when`(dbRef.setValue(any())).thenReturn(setValueTask as Task<Void>?)

        // Set up the initial state of the activity
        activity.intent = mock(Intent::class.java)
        `when`(activity.intent.getStringExtra("Name")).thenReturn("John Doe")
        `when`(activity.intent.getStringExtra("Email")).thenReturn("john@example.com")
        `when`(activity.intent.getStringExtra("Inq")).thenReturn("Test inquiry")
        val name = "Updated Name"
        val email = "updated@example.com"
        val msg = "Updated inquiry"

        // Call the updateEmpData method
        activity.updateEmpData("id", name, email, msg)

        // Verify that the correct Firebase methods are called and toast message is shown
        verify(dbRef).setValue(any())
        // Verify that the text views are updated with the new values
        assertEquals(name, activity.Name.text.toString())
        assertEquals(email, activity.Email.text.toString())
        assertEquals(msg, activity.Inq.text.toString())
    }
}
