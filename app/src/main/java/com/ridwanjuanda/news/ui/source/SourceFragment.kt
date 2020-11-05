package com.ridwanjuanda.news.ui.source

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
import com.ridwanjuanda.news.databinding.FragmentSourceBinding
import com.ridwanjuanda.news.models.Source
import com.ridwanjuanda.news.ui.MainApplication
import com.ridwanjuanda.news.ui.article.ArticleFragment
import com.ridwanjuanda.news.utils.*
import javax.inject.Inject

/**
 * @author Ridwan Juanda
 * @date 05/10/20
 */

class SourceFragment : BaseFragment() {
    companion object {
        fun getInstance(category: String): SourceFragment {
            val fragment = SourceFragment()
            fragment.category = category
            return fragment
        }
    }

    private var category: String? = ""
    private var adapter: SourceAdapter? = null
    private var dataList: List<Source>? = null

    private val appComponents by lazy { MainApplication.appComponents }
    private lateinit var binding: FragmentSourceBinding
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private fun getViewModel(): SourceViewModel {
        return ViewModelProvider(this, viewModelFactory).get(SourceViewModel::class.java)
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        appComponents.inject(this)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_source, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        getViewModel().getSource(category ?: "")
        initObservers()
    }

    private fun initView() {
        adapter = SourceAdapter(dataList, object : AdapterListener {
            override fun onItemClicked(position: Int) {
                parentFragmentManager.beginTransaction()
                    .add(R.id.frameContainer, ArticleFragment.getInstance(dataList?.get(position)?.id ?: ""), SourceFragment::class.java.name)
                    .hide(this@SourceFragment)
                    .addToBackStack(null)
                    .commitAllowingStateLoss()
            }
        })
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = layoutManager
        val itemDecoration = GridSpacingItemDecoration(context = requireContext(), spanCount = 1, spacing = R.dimen._6sdp, includeEdge = true)
        binding.recyclerView.addItemDecoration(itemDecoration)
        binding.recyclerView.adapter = adapter
    }

    private fun initObservers() {
        getViewModel().resultSource.observe(viewLifecycleOwner, Observer { response ->
            response?.let {
                dataList = it.source
                dataList?.let { it1 -> adapter?.setData(it1) }
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

        getViewModel().showLoading.observe(viewLifecycleOwner, Observer { showLoading ->
            if (showLoading) {
                binding.recyclerView.gone()
                binding.tvErrorMessage.gone()
                binding.progress.visible()
            } else {
                binding.progress.gone()
                binding.recyclerView.gone()
                binding.tvErrorMessage.gone()
            }
        })

        getViewModel().showEmpty.observe(viewLifecycleOwner, Observer {
            binding.tvErrorMessage.visible()
            binding.tvErrorMessage.text = getString(R.string.text_no_data)
        })
    }
}