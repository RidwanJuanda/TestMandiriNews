package com.ridwanjuanda.news.ui.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ridwanjuanda.news.R
import com.ridwanjuanda.news.utils.AdapterListener
import kotlinx.android.synthetic.main.item_category.view.*

/**
 * @author Ridwan Juanda
 * @date 05/10/20
 */

class CategoryAdapter internal constructor(private val dataList: List<String>, val callBack: AdapterListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_category, viewGroup, false)
        return WidgetViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val holder = viewHolder as WidgetViewHolder
        holder.itemView.tvCategory.text = dataList[position]
    }

    private inner class WidgetViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        init {
            itemView.setOnClickListener {
                callBack.onItemClicked(adapterPosition)
            }
        }
    }
}