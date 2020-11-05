package com.ridwanjuanda.news.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.ridwanjuanda.news.R
import com.ridwanjuanda.news.base.BaseFragment
import com.ridwanjuanda.news.ui.source.SourceFragment
import com.ridwanjuanda.news.utils.AdapterListener
import com.ridwanjuanda.news.utils.GridSpacingItemDecoration
import kotlinx.android.synthetic.main.fragment_category.view.*

/**
 * @author Ridwan Juanda
 * @date 05/10/20
 */

class CategoryFragment : BaseFragment() {
    companion object {
        val instance: CategoryFragment
            get() = CategoryFragment()
    }

    private val dataList = listOf("general","business","technology","sports","entertainment","health","science")

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView =  inflater.inflate(R.layout.fragment_category, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        val adapter = CategoryAdapter(dataList, object: AdapterListener {
            override fun onItemClicked(position: Int) {
                parentFragmentManager.beginTransaction()
                    .add(R.id.frameContainer, SourceFragment.getInstance(dataList[position]), SourceFragment::class.java.name)
                    .hide(this@CategoryFragment)
                    .addToBackStack(null)
                    .commitAllowingStateLoss()
            }
        })
        val gridLayoutManager = GridLayoutManager(activity, 1, GridLayoutManager.VERTICAL, false)
        rootView.recyclerView.layoutManager = gridLayoutManager
        val itemDecoration = GridSpacingItemDecoration(context = requireContext(), spanCount = 2, spacing = R.dimen._8sdp, includeEdge = true)
        rootView.recyclerView.addItemDecoration(itemDecoration)
        rootView.recyclerView.adapter = adapter
    }
}