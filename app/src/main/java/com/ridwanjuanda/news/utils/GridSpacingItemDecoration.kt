package com.ridwanjuanda.news.utils

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Ridwan Juanda
 * @date 05/10/20
 */

class GridSpacingItemDecoration @JvmOverloads constructor(context: Context,
                                                          @DimenRes spacing: Int,
                                                          private val spanCount: Int,
                                                          private val includeEdge: Boolean = false) : RecyclerView.ItemDecoration() {

    private val spacing: Int = context.resources.getDimensionPixelSize(spacing)

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view) // item position
        val column = position % spanCount // item column
        if (includeEdge) {
            outRect.left = spacing - column * (spacing / spanCount) // spacing - column * ((1f / spanCount) * spacing)
            outRect.right = (column + 1) * (spacing / spanCount) // (column + 1) * ((1f / spanCount) * spacing)
            if (position < spanCount) { // top edge
                outRect.top = spacing
            }
            outRect.bottom = spacing // item bottom
        } else {
            outRect.left = column * (spacing / spanCount) // column * ((1f / spanCount) * spacing)
            outRect.right = spacing - (column + 1) * (spacing / spanCount) // spacing - (column + 1) * ((1f /    spanCount) * spacing)
            if (position >= spanCount) {
                outRect.top = spacing // item top
            }
        }
    }
}