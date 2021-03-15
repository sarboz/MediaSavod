package tj.mediasavod

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import tj.mediasavod.models.Data
import java.util.*

class FirebaseRepository() {

    private val firebaseDB = FirebaseDatabase.getInstance()
    val ref = firebaseDB.getReference("data")
    val data: MutableLiveData<MutableList<Data>> = MutableLiveData()


    fun getLessonsFromFirebase(onResult: (MutableLiveData<MutableList<Data>>) -> Unit) {
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val d: MutableList<Data> = ArrayList<Data>();
                for (postSnapshot in p0.children) {
                    d.add(postSnapshot.getValue(Data::class.java)!!)
                }
                data.value = d
                onResult(data)
            }

            override fun onCancelled(p0: DatabaseError) {
                Log.e("Eeeeeeeeeee", p0.message)
            }
        })

    }

}