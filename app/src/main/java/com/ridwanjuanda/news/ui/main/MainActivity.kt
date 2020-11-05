package com.ridwanjuanda.news.ui.main

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView.OnEditorActionListener
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ridwanjuanda.news.R
import com.ridwanjuanda.news.databinding.ActivityMainBinding
import com.ridwanjuanda.news.ui.MainApplication
import com.ridwanjuanda.news.ui.category.CategoryFragment
import com.ridwanjuanda.news.ui.search.SearchFragment
import javax.inject.Inject

/**
 * @author Ridwan Juanda
 * @date 05/10/20
 */

@Suppress("UNREACHABLE_CODE")
class MainActivity : AppCompatActivity() {
    private val appComponents by lazy { MainApplication.appComponents }
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var binding: ActivityMainBinding
    private fun getViewModel(): MainViewModel {
        return ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponents.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initObservers()
        initView()
    }

    private fun initView() {
        binding.edtSearch.setOnEditorActionListener(OnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                imm?.hideSoftInputFromWindow(v.windowToken, 0)
                val fragment = supportFragmentManager.findFragmentByTag(SearchFragment::class.java.simpleName)
                if (fragment != null) {
                    supportFragmentManager.beginTransaction().remove(fragment).commit()
                    supportFragmentManager.popBackStackImmediate()
                }
                supportFragmentManager.beginTransaction()
                    .add(R.id.frameContainer, SearchFragment.getInstance(null), SearchFragment::class.java.simpleName)
                    .addToBackStack(null)
                    .hide(supportFragmentManager.findFragmentById(R.id.frameContainer)!!)
                    .commitAllowingStateLoss()
                getViewModel().getSearchArticle(binding.edtSearch.text.toString())
                return@OnEditorActionListener true
            }
            false
        })
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameContainer, CategoryFragment.instance, CategoryFragment::class.java.simpleName)
            .commitAllowingStateLoss()
    }

    private fun initObservers() {
        getViewModel().resultSource.observe(this, Observer { response ->
            response?.let {
                val fragment = supportFragmentManager.findFragmentById(R.id.frameContainer)
                if (fragment != null && fragment is SearchFragment) {
                    response.article?.let { it1 ->
                        fragment.setData(it1)
                        binding.edtSearch.setText("")
                    }
                }
            }
        })

        getViewModel().errorMessage.observe(this, Observer {
            var errorMessage = it
            if (errorMessage == "") {
                errorMessage = getString(R.string.text_failed_get_data)
            }
            val fragment = supportFragmentManager.findFragmentById(R.id.frameContainer)
            if (fragment != null && fragment is SearchFragment) {
                fragment.setErrorMessage(errorMessage)
            }
        })

        getViewModel().showLoading.observe(this, Observer { showLoading ->
            val fragment = supportFragmentManager.findFragmentById(R.id.frameContainer)
            if (fragment != null && fragment is SearchFragment) {
                fragment.showLoading(showLoading)
            }
        })

        getViewModel().showEmpty.observe(this, Observer {
            val fragment = supportFragmentManager.findFragmentById(R.id.frameContainer)
            if (fragment != null && fragment is SearchFragment) {
                fragment.showEmpty()
            }
        })
    }

}