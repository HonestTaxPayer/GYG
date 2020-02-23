package gyg.reviews.presentation

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun Fragment.setTitle(resId: Int) {
    (activity as? AppCompatActivity)?.let {
        it.supportActionBar?.title =
            it.getString(resId)
    }
}