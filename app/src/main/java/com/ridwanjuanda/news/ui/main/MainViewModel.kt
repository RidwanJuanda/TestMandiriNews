package com.ridwanjuanda.news.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ridwanjuanda.news.network.AppRepositoryImpl
import com.ridwanjuanda.news.models.ArticleResponse
import com.ridwanjuanda.news.models.ResultData
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Ridwan Juanda
 * @date 05/10/20
 */

class MainViewModel @Inject constructor(private val repositoryImpl: AppRepositoryImpl) : ViewModel() {

    private var _resultSource = MutableLiveData<ArticleResponse>()
    var resultSource: LiveData<ArticleResponse> = _resultSource

    private var _errorMessage = MutableLiveData<String>()
    var errorMessage: LiveData<String> = _errorMessage

    private var _showLoading = MutableLiveData<Boolean>()
    var showLoading: LiveData<Boolean> = _showLoading

    private var _showEmpty = MutableLiveData<Boolean>()
    var showEmpty: LiveData<Boolean> = _showEmpty

    fun getSearchArticle(text: String) {
        _showLoading.postValue(true)
        viewModelScope.launch {
            try {
                when (val response = repositoryImpl.getSearchArticle2(text)) {
                    is ResultData.Success -> {
                        _showLoading.postValue(false)
                        if (response.data.article != null && response.data.article?.size!! > 0) {
                            _resultSource.postValue(response.data)
                        } else {
                            _showEmpty.postValue(true)
                        }
                    }
                    is ResultData.Error -> {
                        _showLoading.postValue(false)
                        _errorMessage.postValue(response.exception.toString())
                    }
                }
            } catch (e: Exception) {
                _showLoading.postValue(false)
                _errorMessage.postValue(e.message)
            }
        }
    }
}