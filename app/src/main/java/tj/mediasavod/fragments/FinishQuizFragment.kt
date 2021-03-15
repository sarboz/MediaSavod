package tj.mediasavod.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import tj.mediasavod.MainViewModel
import tj.mediasavod.R
import tj.mediasavod.models.Lesson


class FinishQuizFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_finish, container, false)
        val pref = requireActivity().getSharedPreferences("lessons", Context.MODE_PRIVATE)

        val model = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        model.lesson.observe(requireActivity(), {
            if (it != null) {
                pref.edit().putString(it.title, "0").apply()
            }
        })

        v.findViewById<Button>(R.id.button).setOnClickListener {
            findNavController().navigate(R.id.action_finishQuizFragment_to_lessonsFragment)
        }
        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigate(R.id.action_finishQuizFragment_to_lessonsFragment)
                }
            }
            )

        return v
    }
}