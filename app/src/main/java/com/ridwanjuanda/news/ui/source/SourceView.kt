package com.ridwanjuanda.news.ui.source

import com.ridwanjuanda.news.base.BaseView
import com.ridwanjuanda.news.models.Source

/**
 * @author Ridwan Juanda
 * @date 05/10/20
 */

@Deprecated(message = "Deprecated change to MVVM")
interface SourceView : BaseView {
    fun onSuccessSource(data: List<Source>)
}