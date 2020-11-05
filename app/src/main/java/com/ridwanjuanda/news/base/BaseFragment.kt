package com.ridwanjuanda.news.base

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment

/**
 * @author Ridwan Juanda
 * @date 05/10/20
 */

abstract class BaseFragment : Fragment() {

    lateinit var rootView: View
    var activity: Context? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.activity = context
    }

    override fun onDetach() {
        super.onDetach()
        this.activity = null
    }

    private fun isRootViewInitialized() = ::rootView.isInitialized

    fun checkIfFragmentNotAttachToActivity(): Boolean {
        return !isAdded || isDetached || context == null || getActivity() == null || !isRootViewInitialized()
    }
}