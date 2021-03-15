package tj.mediasavod

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.ContextMenu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.database.FirebaseDatabase
import tj.mediasavod.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true)
        } catch (e: Exception) {
        }
        super.onCreate(savedInstanceState)
        val a: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val navControler = this.findNavController(R.id.myNavHost)

        a.bottomNav.setupWithNavController(navControler)
         AppBarConfiguration.Builder(
            R.id.lessonsFragment,
            R.id.profileFragment,
            R.id.bookFragment,
            R.id.aboutFragment,
            R.id.quizListFragment
        ).build()
        //  NavigationUI.setupActionBarWithNavController(this, navControler, appBarConfiguration)
        navControler.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.splashFragment -> a.bottomNav.visibility = View.GONE
                R.id.quizFragment -> a.bottomNav.visibility = View.GONE
                R.id.quizStartFragment -> a.bottomNav.visibility = View.GONE
                R.id.finishQuizFragment -> a.bottomNav.visibility = View.GONE
                else -> {
                    a.bottomNav.animate().translationY(1.0f).duration = 1000
                //    Handler().postDelayed(Runnable {
                        a.bottomNav.visibility = View.VISIBLE
                  //  }, 500)
                }
            }
        }
        NavigationUI.setupWithNavController(a.bottomNav, navControler)
    }

    fun Context.toast(s:String){
        Toast.makeText(this,s, Toast.LENGTH_LONG).show()
    }
}