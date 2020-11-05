package com.ridwanjuanda.news.base

/**
 * @author Ridwan Juanda
 * @date 05/10/20
 */

@Deprecated(message = "Deprecated change to MVVM")
interface BaseView{
    fun startLoading()
    fun stopLoading()
    fun setErrorMessage(message:String = "")
    fun showEmpty()
}