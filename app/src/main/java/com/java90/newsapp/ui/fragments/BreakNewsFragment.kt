package com.java90.newsapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.java90.newsapp.R
import com.java90.newsapp.adapters.NewsAdapter
import com.java90.newsapp.util.Resource
import kotlinx.android.synthetic.main.fragment_breaking_news.*

class BreakNewsFragment : BaseFragment() {
    override fun getViewID(): Int = R.layout.fragment_breaking_news

    lateinit var newsAdapter: NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()

        viewModel.getBreakingNews("co")
        viewModel.breakingNews.observe(viewLifecycleOwner,
            Observer { response ->
                when(response) {
                    is Resource.Loading -> {
                        showProgressBar()
                    }
                    is Resource.Success -> {
                        hideProgressBar()
                        response.data?.let {newsResponse ->
                            newsAdapter.differ.submitList(newsResponse.articles)
                        }
                    }
                    is Resource.Failure -> {
                        showProgressBar()
                        tvError.visibility = View.VISIBLE
                        tvError.text = "Error: Revisar conexión a internet"
                    }
                }
            }
        )

        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putParcelable("article", it)
            }
            findNavController().navigate(
                R.id.action_breakNewsFragment_to_articleFragment,
                bundle
            )
        }
    }

    private fun setUpRecyclerView() {
        newsAdapter = NewsAdapter()
        rvBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.GONE
    }
}