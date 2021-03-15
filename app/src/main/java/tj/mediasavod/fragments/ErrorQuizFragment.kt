package tj.mediasavod.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import tj.mediasavod.R


class ErrorQuizFragment : DialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.fragment_error_quiz, container, false)
        isCancelable = false
        val btn = v.findViewById<Button>(R.id.button)
        btn.setOnClickListener {
            dialog?.dismiss()
        }
        return v
    }

    override fun show(manager: FragmentManager, tag: String?) {
        dialog?.window?.attributes?.windowAnimations = android.R.style.Animation_Dialog
        super.show(manager, tag)
    }
}