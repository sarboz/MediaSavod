package tj.mediasavod.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import tj.mediasavod.R


class CorrectQuizFragment : DialogFragment() {


    fun show(manager: FragmentManager, tag: String?, listener: onDismissDialogListener) {
        dialog?.window?.attributes?.windowAnimations = android.R.style.Animation_Dialog
        super.show(manager, tag)
        l = listener

    }

    lateinit var l: onDismissDialogListener
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.fragment_correct_quiz, container, false)
        val btn = v.findViewById<Button>(R.id.button)
        isCancelable = false
        btn.setOnClickListener {
            btn.isEnabled=false;
            this.dismiss()
            l.onDismissDialog()
        }
        return v
    }

    interface onDismissDialogListener {
        fun onDismissDialog()
    }

}