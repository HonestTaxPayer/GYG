package gyg.reviews.presentation

import java.text.SimpleDateFormat
import java.util.*

const val INPUT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssXXX"
const val OUPUT_DATE_FORMAT = "EEEE, MMMM dd, yyyy"

fun String.formatDate(): String {
    try {
        val date =
            SimpleDateFormat(INPUT_DATE_FORMAT, Locale.getDefault())
                .parse(this)

        date?.let {
            val outputFormat =
                SimpleDateFormat(OUPUT_DATE_FORMAT, Locale.getDefault())
            return outputFormat.format(date)
        }
    } catch (ex: Exception) {
        return this
    }
    return this
}