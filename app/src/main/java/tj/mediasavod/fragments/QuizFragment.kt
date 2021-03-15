package tj.mediasavod.fragments

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.RadioButton
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import tj.mediasavod.MainViewModel
import tj.mediasavod.R
import tj.mediasavod.databinding.FragmentQuizBinding


class QuizFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    lateinit var model: MainViewModel
    lateinit var quiz: FragmentQuizBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        model = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        quiz =
            DataBindingUtil.inflate(inflater, R.layout.fragment_quiz, container, false)
        model.quizList.observe(requireActivity(), Observer {
            quiz.quizData = it
            val rnd =
                listOf(it.option1!!, it.option2!!, it.option3!!, it.answer!!).shuffled()

            val s = (1 + model.i).toString() + "/" + model.lesson.value?.quiz?.size
            val q = QuizBinding(rnd[0], rnd[1], rnd[2], rnd[3], s)
            quiz.quiz = q
        })
        quiz.clickHandler = this
        return quiz.root
    }

    private lateinit var sound: MediaPlayer

    fun startButtonClick() {
        val selectedRadio = view?.findViewById<RadioButton>(quiz.radioGroup.checkedRadioButtonId)

        if (selectedRadio?.id != null) {
            model.checkQuiz(selectedRadio.text.toString(), {//success
                val d = CorrectQuizFragment()
                sound = MediaPlayer.create(requireContext(), R.raw.win)
                sound.start()
                fragmentManager?.let {
                    d.show(it, "dd", object : CorrectQuizFragment.onDismissDialogListener {
                        override fun onDismissDialog() {
                            model.nextQuiz()
                            val animation =
                                AnimationUtils.loadAnimation(context, R.anim.slide_to_left)
                            val animation1 =
                                AnimationUtils.loadAnimation(context, R.anim.slide_to_rigtht)
                            quiz.container.startAnimation(animation1)
                            animation1.setAnimationListener(object : Animation.AnimationListener {
                                override fun onAnimationStart(animation: Animation?) {}

                                override fun onAnimationEnd(anim: Animation?) {
                                    quiz.radioGroup.clearCheck()
                                    quiz.container.startAnimation(animation)
                                }

                                override fun onAnimationRepeat(animation: Animation?) {
                                }
                            })
                        }
                    })
                }

            }, { // error message selected
                sound = MediaPlayer.create(requireContext(), R.raw.lose)
                sound.start()
                quiz.radioGroup.clearCheck()
                fragmentManager?.let { ErrorQuizFragment().show(it, "dd") }
            }, {
                // quiz finished
                findNavController().navigate(R.id.action_quizFragment_to_finishQuizFragment)
            })
        } else {
            Snackbar.make(
                activity?.findViewById<View>(android.R.id.content)!!,
                "Яке аз ҷавобҳоро интихоб кунед",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    data class QuizBinding(
        var s1: String?,
        var s2: String?,
        var s3: String?,
        var s4: String?,
        var counter: String = ""
    ) {}
}