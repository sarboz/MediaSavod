package tj.mediasavod.fragments

import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.os.Build
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import tj.mediasavod.R

class AboutFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_about, container, false)


        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().finish()

                }
            }
            )

        val text = v.findViewById<TextView>(R.id.text_about)
        val text_link = v.findViewById<TextView>(R.id.text_link)

        text_link.setOnClickListener {
            findNavController().navigate(R.id.action_aboutFragment_to_lessonsFragment)
        }

        text.text=HtmlCompat.fromHtml(" <p>Бозии ройгони омӯзишии <strong>\"МедиаДоно\"</strong></p>\n" +
                "        <p>Салом, муштарии гиромӣ! <strong>\"МедиаДоно\"</strong> - яке аз нахустин бозии ройгони омӯзишӣ дар соҳаи саводи иттилоотӣ-расонаӣ дар Тоҷикистон аст. Кӯмак ба наврасону навҷавонон ва инчунин ба волидайни онҳо ҷиҳати расонаомӯзӣ ва баланд бардоштани саводи расонаӣ ҳадафи асосии ин барнома аст. <strong>\"МедиаДоно\"</strong> ба шумо дар дарки беҳтари моҳияти расонаҳо, ҳаракат дар фазои иттилоотӣ, ёдгирии муносибати интиқодӣ ба ВАО, ташхиси иттилооти сифатнок ва боэътимод, шиносоӣ бо талошҳои дасткории афкори ҷомеа ва муқовимат ба он, шеваҳои далелсанҷӣ, ёд гирифтани ҳуқуқи иттилоот ва имкони истифода аз онҳо, инчунин шеваҳои эҷоди муҳтаво кӯмак хоҳад кард.</p>\n" +
                "    <p>Барои ин ба шумо лозим аст, ки дарсҳоро ё дар шакли ихтисоршуда, ё муфассал омӯзед ва баъди омӯзиш санҷишҳои тестиро анҷом диҳед. Дар ҷараёни бозии омӯзишӣ шумо бобати иттилоот ва расона, фарқи онҳо инчунин тафовути расонаҳои рақамӣ ва <em>рӯзноманигории навин</em>, <span style=\"text-decoration: underline;\"><em>далелсанҷӣ</em> </span>ва шеваҳои дасткорӣ, <span style=\"text-decoration: underline;\"><em>зарурияти тафаккури интиқодӣ</em></span>, <span style=\"text-decoration: underline;\"><em>роҳҳои таъмини амният</em></span> дар фазои маҷозӣ ва ғайра маълумоти зарурӣ пайдо мекунед. Барои маълумоти муфассал пайдо кардан, зарур аст ҳамаи 10 дарсро аз худ карда, ҳамаи 10 зинаи санҷишҳои тестиро хотима диҳед. Агар шумо ба суолҳо посухи нодуруст пешниҳод кунед, барнома Шумо барои такроран супоридани санҷиш бозпас равон мекунад. Инак, аз <em>\"МедиаДоно\"</em> барои расонаомӯзӣ ба хотири баланд бардоштани малакаю маҳорат ва саводи расонаии худ самаранок истифода кунед.</p>Афзораки МедиаДоно дар доираи лоиҳаи \"Таъсиси махзани интерактивии онлайнии Mediasavod.tj оид ба рушди саводи иттилоотию расонаӣ ва тафаккури интиқодӣ\" бо дастгирии моддии Шуъбаи Ташкилоти Байналмилалии Институти \"Ҷамъияти Кушода\" - \"Бунёди Мадад\" дар Тоҷикистон эҷод шудааст.",HtmlCompat.FROM_HTML_MODE_LEGACY)

       // text.movementMethod = ScrollingMovementMethod()
        return v
    }
}