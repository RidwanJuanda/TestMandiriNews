package com.ridwanjuanda.news.models

/**
 * @author Ridwan Juanda
 * @date 04/10/20
 */

sealed class ResultData<out T : Any> {
    data class Success<out T : Any>(val data: T) : ResultData<T>()
    data class Error(val exception: Exception) : ResultData<Nothing>()
}