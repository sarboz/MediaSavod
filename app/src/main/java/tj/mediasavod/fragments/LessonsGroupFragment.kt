package tj.mediasavod.fragments

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import tj.mediasavod.ItemClickListener
import tj.mediasavod.MainViewModel
import tj.mediasavod.R
import tj.mediasavod.adapters.LessonsGroupAdapter
import tj.mediasavod.models.Data


class LessonsGroupFragment : Fragment(), ItemClickListener {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lessons_group, container, false)
        val model = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().finish()

                }
            }
            )

        val adapter = LessonsGroupAdapter(this)
        val rvlist = view.findViewById<RecyclerView>(R.id.rvlist)
        rvlist.adapter = adapter
        model.data.observe(requireActivity(), {

            adapter.list = it
            adapter.notifyDataSetChanged()
        })


//        if (pref?.getString("name", "").equals("")) {
//            Handler().postDelayed(object : Runnable {
//                override fun run() {
//                    val bottomSheetDialog: UserDialogFragment = UserDialogFragment()
//                    bottomSheetDialog.show(parentFragmentManager, "Bottom Sheet Dialog Fragment")
//                }
//            }, 2000)
//        }
        return view
    }

    override fun onItemsClickListener(item: Data) {
        val model = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        model.setLesson(item.lesson!![0])
        view?.findNavController()!!.navigate(
            R.id.action_lessonsFragment_to_quizStartFragment
        )
    }
}