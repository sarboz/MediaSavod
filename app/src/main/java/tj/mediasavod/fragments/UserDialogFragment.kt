package tj.mediasavod.fragments

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import tj.mediasavod.R


class UserDialogFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_user_dialog_list_dialog, container, false)
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        val b =v.findViewById<Button>(R.id.save_name)
        val name=v.findViewById<EditText>(R.id.personName)
        b.setOnClickListener {
            if (name.text.isNotEmpty()) {
                val pref = context?.getSharedPreferences("da", Context.MODE_PRIVATE)
                pref?.edit()?.putString("name", name.text.toString())?.apply()
                dialog?.dismiss()
            }
        }
        return v
    }

    override fun setupDialog(dialog: Dialog, style: Int) {
        val contentView = View.inflate(context, R.layout.fragment_user_dialog_list_dialog, null)
        dialog.setContentView(contentView)
        dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
    }
}