package com.java90.newsapp.ui.fragments


import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.java90.newsapp.R
import kotlinx.android.synthetic.main.fragment_article.*

class ArticleFragment : BaseFragment() {
    override fun getViewID(): Int = R.layout.fragment_article

    private val args: ArticleFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val article = args.article
        webView.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }

        fab.setOnClickListener{
            viewModel.saveArticle(article)
            Snackbar.make(view, "Articulo guardado exitosamente", Snackbar.LENGTH_SHORT).show()
        }
    }
}