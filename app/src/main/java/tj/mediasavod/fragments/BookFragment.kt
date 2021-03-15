package tj.mediasavod.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import com.github.barteksc.pdfviewer.PDFView
import com.github.barteksc.pdfviewer.link.DefaultLinkHandler
import tj.mediasavod.R


class BookFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(
            R.layout.fragment_book,
            container, false
        )
        val p = v.findViewById<PDFView>(R.id.pdfView)

        p.fromAsset("savodi_rasonai.pdf")
            .spacing(0)
            .enableAnnotationRendering(true)
            .spacing(0)
            .autoSpacing(false)
            .linkHandler(DefaultLinkHandler(p))
            .swipeHorizontal(false)
            .load()
        return v
    }
}