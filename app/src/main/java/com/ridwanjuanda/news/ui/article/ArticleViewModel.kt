package com.ridwanjuanda.news.ui.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ridwanjuanda.news.network.AppRepositoryImpl
import com.ridwanjuanda.news.models.ArticleResponse
import com.ridwanjuanda.news.models.ResultData
import com.ridwanjuanda.news.utils.isNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Ridwan Juanda
 * @date 05/10/20
 */

class ArticleViewModel @Inject constructor(private val repositoryImpl: AppRepositoryImpl) : ViewModel() {

    private var _resultSource = MutableLiveData<ArticleResponse>()
    var resultSource: LiveData<ArticleResponse> = _resultSource

    private var _errorMessage = MutableLiveData<String>()
    var errorMessage: LiveData<String> = _errorMessage

    private var _loadMoreErrorMessage = MutableLiveData<String>()
    var loadMoreErrorMessage: LiveData<String> = _loadMoreErrorMessage

    private var _showLoading = MutableLiveData<Boolean>()
    var showLoading: LiveData<Boolean> = _showLoading

    private var _showLoadingLoadMore = MutableLiveData<Boolean>()
    var showLoadingLoadMore: LiveData<Boolean> = _showLoadingLoadMore

    private var _showEmpty = MutableLiveData<Boolean>()
    var showEmpty: LiveData<Boolean> = _showEmpty

    fun getArticleByCategory(source: String, page: Int,  isLoadMore: Boolean) {
        if (isLoadMore) {
            _showLoadingLoadMore.postValue(true)
        } else {
            _showLoading.postValue(true)
        }
        viewModelScope.launch {
            try {
                when (val response = repositoryImpl.getArticleByCategory2(source, page)) {
                    is ResultData.Success -> {
                        _showLoading.postValue(false)
                        _showLoadingLoadMore.postValue(false)
                        if (isNotNull(response.data.article)) {
                            _resultSource.postValue(response.data)
                        } else {
                            _showEmpty.postValue(!isLoadMore)
                        }
                    }
                    is ResultData.Error -> {
                        _showLoading.postValue(false)
                        _showLoadingLoadMore.postValue(false)
                        if (isLoadMore) {
                            _loadMoreErrorMessage.postValue(response.exception.toString())
                        } else {
                            _errorMessage.postValue(response.exception.toString())
                        }
                    }
                }
            } catch (e: Exception) {
                _showLoading.postValue(false)
                _showLoadingLoadMore.postValue(false)
                if (isLoadMore) {
                    _loadMoreErrorMessage.postValue(e.message)
                } else {
                    _errorMessage.postValue(e.message)
                }
            }
        }
    }
}