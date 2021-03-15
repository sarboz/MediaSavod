package tj.mediasavod.fragments

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import tj.mediasavod.FirebaseRepository
import tj.mediasavod.MainViewModel
import tj.mediasavod.R
import tj.mediasavod.databinding.FragmentQuizStartBinding
import tj.mediasavod.models.Data
import tj.mediasavod.models.Lesson


class QuizStartFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val model = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        val quiz: FragmentQuizStartBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_quiz_start, container, false)
        quiz.clickHandler = this

        model.lesson.observe(requireActivity(), Observer<Lesson> {
            quiz.item = it
        })
        quiz.textView2.movementMethod=ScrollingMovementMethod()
        return quiz.root
    }

//    fun startButtonClick(view:View) {
//        view.findNavController().navigate(R.id.action_quizStartFragment_to_quizFragment)
//    }

}