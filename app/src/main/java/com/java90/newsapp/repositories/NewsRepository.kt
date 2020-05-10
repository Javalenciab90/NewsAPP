package com.java90.newsapp.repositories

import com.java90.newsapp.api.RetrofitInstance
import com.java90.newsapp.db.ArticleDatabase
import com.java90.newsapp.models.Article

class NewsRepository(private val db: ArticleDatabase) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery, pageNumber)

    suspend fun insert(article: Article) = db.articleDao().insert(article)

    fun getSaveNews() = db.articleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.articleDao().delete(article)
}