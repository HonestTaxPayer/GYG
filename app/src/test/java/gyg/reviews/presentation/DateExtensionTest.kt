package gyg.reviews.presentation

import junit.framework.Assert.assertEquals
import org.junit.Test

class DateExtensionTest {

    @Test
    fun `format date with correct input`() {
        val inputDate = "2020-02-19T15:50:36+01:00"
        val outputDate = "Wednesday, February 19, 2020"

        assertEquals(outputDate, inputDate.formatDate())
    }

    @Test
    fun `format date with wrong input`() {
        val inputDate = "2020-02-19T15:50:36"

        assertEquals(inputDate, inputDate.formatDate())
    }
}