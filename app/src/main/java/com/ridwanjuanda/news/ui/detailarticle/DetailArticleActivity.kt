package com.ridwanjuanda.news.ui.detailarticle

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.ridwanjuanda.news.R
import com.ridwanjuanda.news.databinding.ActivityDetailArticleBinding
import com.ridwanjuanda.news.ui.MainApplication
import javax.inject.Inject

/**
 * @author Ridwan Juanda
 * @date 05/10/20
 */

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class DetailArticleActivity : AppCompatActivity() {

    companion object {
        private const val ARG_GET_URL = "content-url"

        fun startActivity(activity: Activity, url: String?) {
            val intent = Intent(activity, DetailArticleActivity::class.java)
            intent.putExtra(ARG_GET_URL, url ?: "https://www.google.co.id")
            activity.startActivity(intent)
        }
    }

    private val appComponents by lazy { MainApplication.appComponents }
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var binding: ActivityDetailArticleBinding
    private fun getViewModel(): DetailArticleViewModel {
        return ViewModelProvider(this, viewModelFactory).get(DetailArticleViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponents.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_article)
        initView()
    }

    private fun initView() {
        setWebViewSettings()
        val url = intent.getStringExtra(ARG_GET_URL)
        binding.webview.loadUrl(url)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setWebViewSettings() {
        binding.webview.settings.javaScriptEnabled = true
        binding.webview.settings.builtInZoomControls = false
        binding.webview.settings.supportMultipleWindows()
        binding.webview.settings.setSupportZoom(false)
        binding.webview.settings.javaScriptCanOpenWindowsAutomatically = true
        binding.webview.settings.useWideViewPort = false
        binding.webview.settings.domStorageEnabled = true
        binding.webview.settings.saveFormData = false
        binding.webview.settings.useWideViewPort = false
        binding.webview.settings.pluginState = WebSettings.PluginState.ON
        binding.webview.clearCache(true)

        binding.webview.webViewClient = object: WebViewClient() {
            @TargetApi(Build.VERSION_CODES.N)
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return handleUrl(request?.url.toString())
            }
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                return handleUrl(url!!)
            }
        }

        binding.webview.webChromeClient = object: WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                try {
                    if (newProgress < 100) {
                        //webview.visibility = View.GONE
                        binding.progress.visibility = View.VISIBLE
                    } else if (newProgress == 100) {
                        binding.webview.visibility = View.VISIBLE
                        binding.progress.visibility = View.GONE
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun handleUrl(url: String): Boolean {
        Log.d("URL_WEBVIEW", url)
        return false
    }


}