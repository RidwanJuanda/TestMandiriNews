package com.ridwanjuanda.news.ui.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ridwanjuanda.news.R
import com.ridwanjuanda.news.base.BaseFragment
import com.ridwanjuanda.news.databinding.FragmentArticleBinding
import com.ridwanjuanda.news.models.Article
import com.ridwanjuanda.news.ui.MainApplication
import com.ridwanjuanda.news.ui.detailarticle.DetailArticleActivity
import com.ridwanjuanda.news.utils.*
import javax.inject.Inject

/**
 * @author Ridwan Juanda
 * @date 05/10/20
 */

class ArticleFragment : BaseFragment() {

    companion object {
        fun getInstance(source: String): ArticleFragment {
            val fragment = ArticleFragment()
            fragment.source = source
            return fragment
        }
    }

    private var source: String? = ""
    private var adapter: ArticleAdapter? = null
    private var dataList: ArrayList<Article>? = null
    private var endlessRecyclerScrollListener: EndlessRecyclerOnScrollListener? = null
    private var page: Int = 1

    private val appComponents by lazy { MainApplication.appComponents }
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var binding: FragmentArticleBinding
    private fun getViewModel(): ArticleViewModel {
        return ViewModelProvider(this, viewModelFactory).get(ArticleViewModel::class.java)
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        appComponents.inject(this)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_article, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        getViewModel().getArticleByCategory(source ?: "", page, false)
        initObservers()
    }

    private fun initView() {
        adapter = ArticleAdapter(dataList, object : AdapterListener {
            override fun onItemClicked(position: Int) {
                DetailArticleActivity.startActivity(requireActivity(), dataList?.get(position)?.url)
            }
        })
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = layoutManager
        val itemDecoration = GridSpacingItemDecoration(context = requireContext(), spanCount = 1, spacing = R.dimen._6sdp, includeEdge = true)
        binding.recyclerView.addItemDecoration(itemDecoration)
        endlessRecyclerScrollListener = object: EndlessRecyclerOnScrollListener(layoutManager) {
            override fun onLoadMore(currentPage: Int) {
                getViewModel().getArticleByCategory(source ?: "", page, true)
            }
        }
        binding.recyclerView.addOnScrollListener(endlessRecyclerScrollListener!!)
        binding.recyclerView.adapter = adapter
    }

    private fun initObservers() {
        getViewModel().resultSource.observe(viewLifecycleOwner, Observer { response ->
            response?.let {
                if (dataList == null || page == 1){
                    dataList = ArrayList()
                }
                response.article?.let { it1 -> dataList?.addAll(it1) }
                adapter?.setData(dataList!!)
                page++
                binding.recyclerView.visible()
            }
        })

        getViewModel().errorMessage.observe(viewLifecycleOwner, Observer {
            var errorMessage = it
            if (errorMessage == "") {
                errorMessage = getString(R.string.text_failed_get_data)
            }
            binding.tvErrorMessage.visible()
            binding.tvErrorMessage.text = errorMessage
        })

        getViewModel().loadMoreErrorMessage.observe(viewLifecycleOwner, Observer {
            if (isNotNull(dataList)) {
                binding.recyclerView.visible()
            }
            if (endlessRecyclerScrollListener != null) {
                binding.recyclerView.removeOnScrollListener(endlessRecyclerScrollListener!!)
            }
        })

        getViewModel().showLoading.observe(viewLifecycleOwner, Observer { showLoading ->
            if (showLoading) {
                binding.progress.visible()
                binding.recyclerView.gone()
                binding.tvErrorMessage.gone()
            } else {
                binding.progress.gone()
                binding.recyclerView.gone()
                binding.tvErrorMessage.gone()
            }
        })

        getViewModel().showLoadingLoadMore.observe(viewLifecycleOwner, Observer {})

        getViewModel().showEmpty.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.tvErrorMessage.visible()
                binding.tvErrorMessage.text = getString(R.string.text_no_data)
            } else {
                if (isNotNull(dataList)) {
                    binding.recyclerView.visible()
                }
                if (endlessRecyclerScrollListener != null) {
                    binding.recyclerView.removeOnScrollListener(endlessRecyclerScrollListener!!)
                }
            }
        })
    }
}