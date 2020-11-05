package com.ridwanjuanda.news.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ridwanjuanda.news.R
import com.ridwanjuanda.news.base.BaseFragment
import com.ridwanjuanda.news.databinding.FragmentArticleBinding
import com.ridwanjuanda.news.models.Article
import com.ridwanjuanda.news.ui.MainApplication
import com.ridwanjuanda.news.ui.article.ArticleAdapter
import com.ridwanjuanda.news.ui.detailarticle.DetailArticleActivity
import com.ridwanjuanda.news.utils.AdapterListener
import com.ridwanjuanda.news.utils.GridSpacingItemDecoration
import com.ridwanjuanda.news.utils.gone
import com.ridwanjuanda.news.utils.visible
import javax.inject.Inject

/**
 * @author Ridwan Juanda
 * @date 05/10/20
 */

class SearchFragment : BaseFragment() {
    companion object {
        fun getInstance(data: List<Article>?): SearchFragment {
            val fragment = SearchFragment()
            fragment.dataList = data
            return fragment
        }
    }

    private var adapter: ArticleAdapter? = null
    private var dataList: List<Article>? = null

    private val appComponents by lazy { MainApplication.appComponents }
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var binding: FragmentArticleBinding
    private fun getViewModel(): SearchViewModel {
        return ViewModelProvider(this, viewModelFactory).get(SearchViewModel::class.java)
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        appComponents.inject(this)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_article, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        adapter = ArticleAdapter(dataList, object : AdapterListener {
            override fun onItemClicked(position: Int) {
                DetailArticleActivity.startActivity(requireActivity(), dataList?.get(position)?.url)
            }
        })
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = layoutManager
        val itemDecoration = GridSpacingItemDecoration(context = requireContext(), spanCount = 1, spacing = R.dimen._8sdp, includeEdge = true)
        binding.recyclerView.addItemDecoration(itemDecoration)
        binding.recyclerView.adapter = adapter
    }

    fun setData(data: List<Article>) {
        dataList = data
        adapter?.setData(dataList!!)
        binding.recyclerView.visible()
    }

    fun showLoading(isShow: Boolean) {
        if (isShow) {
            binding.progress.visible()
            binding.recyclerView.gone()
            binding.tvErrorMessage.gone()
        } else {
            binding.progress.gone()
            binding.recyclerView.gone()
            binding.tvErrorMessage.gone()
        }
    }

    fun showEmpty() {
        binding.tvErrorMessage.visible()
        binding.tvErrorMessage.text = getString(R.string.text_no_data)
    }

    fun setErrorMessage(message: String) {
        var errorMessage = message
        if (errorMessage == "") {
            errorMessage = getString(R.string.text_failed_get_data)
        }
        binding.tvErrorMessage.visible()
        binding.tvErrorMessage.text = errorMessage
    }
}