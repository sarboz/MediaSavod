package tj.mediasavod.models

import android.widget.ImageView
import androidx.databinding.BaseObservable
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import pl.droidsonroids.gif.GifImageView
import tj.mediasavod.R


data class Data(
    var id: Int = 0,
    var img: String? = "",
    var name: String? = "",
    var title: String? = "",
    var isRead:Boolean=false,
    var lesson: List<Lesson>? = null
)

data class Lesson(
    var title: String? = "",
    var text: String? = "",
    var img: String? = "",
    var isRead:String="",
    var quiz: List<Quiz>? = null
)

data class Quiz(
    var question: String? = "",
    var answer: String? = "",
    var option1: String? = "",
    var option2: String? = "",
    var option3: String? = "",
    var img: String? = ""
) {
    companion object {
        @JvmStatic
        @BindingAdapter("image")
        fun loadImg(view: GifImageView, url: String) {
            Glide.with(view.context)
                .load(url)
                .into(view)
        }
    }
}
