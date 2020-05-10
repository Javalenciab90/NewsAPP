package com.java90.newsapp.ui

import androidx.lifecycle.*
import com.java90.newsapp.models.Article
import com.java90.newsapp.models.NewsResponse
import com.java90.newsapp.repositories.NewsRepository
import com.java90.newsapp.util.Resource
import kotlinx.coroutines.launch

class NewsViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    val breakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    private var breakingNewsPage = 1

    val searchNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    private var searchNewsPage = 1

    fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())
        try {
            val response = newsRepository.getBreakingNews(countryCode, breakingNewsPage)
            if (response.isSuccessful) {
                response.body()?.let { resultResponse ->
                    searchNews.postValue(Resource.Success(resultResponse))
                }
            }
        }catch (e: Throwable) {
            breakingNews.postValue(Resource.Failure(e.message.toString()))
        }
    }

    fun getSearchNews(searchQuery: String) = viewModelScope.launch {
        searchNews.postValue(Resource.Loading())
        try {
            val response = newsRepository.searchNews(searchQuery, searchNewsPage)
            if (response.isSuccessful) {
                response.body()?.let {resultResponse ->
                    searchNews.postValue(Resource.Success(resultResponse))
                }
            }
        }catch (e: Throwable) {
            searchNews.postValue(Resource.Failure(e.message.toString()))
        }
    }

    fun saveArticle(article: Article) = viewModelScope.launch {
        newsRepository.insert(article)
    }

    fun getSaveNews() = newsRepository.getSaveNews()

    fun deleteArticle(article: Article) = viewModelScope.launch {
        newsRepository.deleteArticle(article)
    }
}