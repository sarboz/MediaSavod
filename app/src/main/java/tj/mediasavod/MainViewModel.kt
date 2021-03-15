package tj.mediasavod

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tj.mediasavod.models.Data
import tj.mediasavod.models.Lesson
import tj.mediasavod.models.Quiz

class MainViewModel : ViewModel() {
    private val repository: FirebaseRepository = FirebaseRepository()
    val data: MutableLiveData<MutableList<Data>> = MutableLiveData()
    var i: Int = 0
    val quizList: MutableLiveData<Quiz> = MutableLiveData()
    fun refresh(success: () -> Unit) {
        repository.getLessonsFromFirebase {
            data.value = it.value
            success()
        }
    }

    val lesson: MutableLiveData<Lesson> = MutableLiveData<Lesson>()
    fun setLesson(l: Lesson) {
        i = 0
        lesson.value = l
        val dd = lesson.value?.quiz?.get(i)
        quizList.value = dd
    }

    fun checkQuiz(result: String, success: () -> Unit, error: () -> Unit, finish: () -> Unit) {

        if (result == lesson.value?.quiz?.get(i)?.answer) {
            success()
            if (i >= lesson.value?.quiz?.size!! - 1) {
                finish()
                return
            }

        } else {
            error()
        }

    }

    fun nextQuiz() {
        if (i >= lesson.value?.quiz?.size!! - 1)
            return
        ++i
        val dd = lesson.value?.quiz?.get(i)
        quizList.postValue(dd)
    }
}