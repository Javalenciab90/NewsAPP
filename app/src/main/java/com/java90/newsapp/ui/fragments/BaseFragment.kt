package com.java90.newsapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.java90.newsapp.ui.NewsActivity
import com.java90.newsapp.ui.NewsViewModel

abstract class BaseFragment : Fragment() {
    abstract fun getViewID(): Int

    lateinit var viewModel: NewsViewModel

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(getViewID(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as NewsActivity).viewModel

    }


}