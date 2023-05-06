import android.content.Intent
import android.view.View
import androidx.cardview.widget.CardView
import com.example.job_portal.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class InquiryMainActivityTest {

    private lateinit var inquiryMainActivity: InquiryMainActivity

    @Before
    fun setup() {
        inquiryMainActivity = InquiryMainActivity()
    }

    @Test
    fun testButtonClick_StartsFetchingActivity() {
        val mockIntent = mock(Intent::class.java)
        val mockCardView = mock(CardView::class.java)

        // Mock the startActivity method
        `when`(mockCardView.setOnClickListener(any())).thenAnswer {
            val onClickListener = it.getArgument(0) as View.OnClickListener
            onClickListener.onClick(mockCardView)
        }
        `when`(mockCardView.context).thenReturn(inquiryMainActivity)
        `when`(inquiryMainActivity.intent).thenReturn(mockIntent)

        // Call the onClick listener of the btnI CardView
        inquiryMainActivity.btnI.performClick()

        // Verify that the FetchingActivity is started
        verify(inquiryMainActivity).startActivity(mockIntent)
    }

    @Test
    fun testButtonClick_StartsFreelanceActivity() {
        val mockIntent = mock(Intent::class.java)
        val mockCardView = mock(CardView::class.java)

        // Mock the startActivity method
        `when`(mockCardView.setOnClickListener(any())).thenAnswer {
            val onClickListener = it.getArgument(0) as View.OnClickListener
            onClickListener.onClick(mockCardView)
        }
        `when`(mockCardView.context).thenReturn(inquiryMainActivity)
        `when`(inquiryMainActivity.intent).thenReturn(mockIntent)

        // Call the onClick listener of the btnfree CardView
        inquiryMainActivity.btnfree.performClick()

        // Verify that the FreelanceActivity is started
        verify(inquiryMainActivity).startActivity(mockIntent)
    }

    // Add similar tests for other button click events (btnInsertData, btnprofile, Jobview)
}
