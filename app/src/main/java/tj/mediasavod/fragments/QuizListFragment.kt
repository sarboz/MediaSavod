package tj.mediasavod.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import tj.mediasavod.ItemClickListener
import tj.mediasavod.MainViewModel
import tj.mediasavod.R
import tj.mediasavod.adapters.QuizAdapter
import tj.mediasavod.models.Data

class QuizListFragment : Fragment(), ItemClickListener {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_quiz_list, container, false)
        val prefLessons = requireActivity().getSharedPreferences("lessons", Context.MODE_PRIVATE)
        val model = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().finish()
                }
            })

        val adapter = QuizAdapter(this)
        val rvlist = v.findViewById<RecyclerView>(R.id.rvlist)
        rvlist?.adapter = adapter
        model.data.observe(requireActivity(), {

            var count=0;
            it.forEach {
                if(prefLessons.getString(it.name,"1").equals("0")){
                    count++
                    it.isRead=true
                }
            }
            if(count==10) {
                FinishDialogFragment().show(requireFragmentManager(), "dialog")
            }

            adapter.list = it
            adapter.notifyDataSetChanged()
        })

        return v
    }

    override fun onItemsClickListener(item: Data) {
        val model = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        model.setLesson(item.lesson!![0])
        view?.findNavController()!!.navigate(
            R.id.action_quizListFragment_to_quizFragment
        )
    }

}