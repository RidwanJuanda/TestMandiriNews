package com.ridwanjuanda.news.ui.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ridwanjuanda.news.network.AppRepositoryImpl
import com.ridwanjuanda.news.models.ResultData
import com.ridwanjuanda.news.models.SourceResponse
import com.ridwanjuanda.news.utils.isNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Ridwan Juanda
 * @date 05/10/20
 */

class SourceViewModel @Inject constructor(private val repositoryImpl: AppRepositoryImpl) : ViewModel() {

    private var _resultSource = MutableLiveData<SourceResponse>()
    var resultSource: LiveData<SourceResponse> = _resultSource

    private var _errorMessage = MutableLiveData<String>()
    var errorMessage: LiveData<String> = _errorMessage

    private var _showLoading = MutableLiveData<Boolean>()
    var showLoading: LiveData<Boolean> = _showLoading

    private var _showEmpty = MutableLiveData<Boolean>()
    var showEmpty: LiveData<Boolean> = _showEmpty

    fun getSource(category: String) {
        _showLoading.postValue(true)
        viewModelScope.launch {
            try {
                when (val response = repositoryImpl.getSourceByCategory2(category)) {
                    is ResultData.Success -> {
                        _showLoading.postValue(false)
                        if (isNotNull(response.data.source)) {
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