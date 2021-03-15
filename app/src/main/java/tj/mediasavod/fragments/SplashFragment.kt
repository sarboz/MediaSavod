package tj.mediasavod.fragments

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.rubengees.introduction.Slide
import tj.mediasavod.MainViewModel
import tj.mediasavod.R

class SplashFragment : Fragment() {

    val pref: SharedPreferences by lazy {
        requireContext().getSharedPreferences(
            "appLancher",
            Context.MODE_PRIVATE
        )
    }
    lateinit var img: ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_splash, container, false)
        val model = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        go(model)

        img = v.findViewById(R.id.imageView4)
        return v
    }


    private fun show(model: MainViewModel) {
        Snackbar.make(
            activity?.findViewById<View>(android.R.id.content)!!,
            "Пайвастшавӣ ба интернет нест",
            Snackbar.LENGTH_INDEFINITE
        ).setAction("Такрор") {
            go(model)
        }.show()
    }

    private fun go(model: MainViewModel) {

        if (isOnline()) {
            model.refresh {
                if (pref.getBoolean("lanch", false)) {

//                    IntroductionBuilder(requireActivity())
//                        .withOnSlideListener(object : OnSlideListener() {
//                            override fun onSlideInit(
//                                position: Int,
//                                title: TextView?,
//                                image: ImageView,
//                                description: TextView?
//                            ) {
//                                super.onSlideInit(position, title, image, description)
//                                if (slide().size - 1 == position) {
//                                    findNavController().navigate(R.id.action_splashFragment_to_lessonsFragment)
//                                }
//                            }
//                        })
//                        .withSlides(slide())
//                        .introduceMyself()

                    findNavController().navigate(R.id.action_splashFragment_to_aboutFragment)
                    pref.edit().putBoolean("lanch", false).apply()
                } else {
                    findNavController().navigate(R.id.action_splashFragment_to_aboutFragment)
                }
            }
        } else
            show(model)
    }

    private fun isOnline(): Boolean {
        val connMgr = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connMgr.activeNetworkInfo
        return networkInfo?.isConnected == true
    }


    private fun slide(): MutableList<Slide> {
        val list: MutableList<Slide> = mutableListOf()

        list.add(
            Slide().withColor(ContextCompat.getColor(requireContext(), R.color.logoColor))
                .withDescription("hbgjhgjgjh").withTitle("hjjkjhk")
                .withImage(R.drawable.boy)
        )
        list.add(
            Slide().withColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.design_default_color_primary_variant
                )
            ).withDescription("hbgjhgjgjh").withTitle("hjjkjhk")
        )
        list.add(
            Slide().withColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.error_quiz
                )
            ).withDescription("hbgjhgjgjh").withTitle("hjjkjhk")
                .withImage(R.drawable.boy)
        )
        list.add(
            Slide().withColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.currect_quiz
                )
            ).withDescription("hbgjhgjgjh").withTitle("hjjkjhk")
                .withImage(R.drawable.girl)
        )


        return list
    }
}