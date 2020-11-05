package com.ridwanjuanda.news.ui.source

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ridwanjuanda.news.R
import com.ridwanjuanda.news.models.Source
import com.ridwanjuanda.news.utils.AdapterListener
import com.ridwanjuanda.news.utils.getHtmlFromString
import kotlinx.android.synthetic.main.item_source.view.*

/**
 * @author Ridwan Juanda
 * @date 05/10/20
 */

class SourceAdapter internal constructor(private var dataList: List<Source>?, val callBack: AdapterListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_source, viewGroup, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList?.size ?: 0
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val holder = viewHolder as ViewHolder
        val data = dataList?.get(position)
        holder.itemView.tvName.text = getHtmlFromString(data?.name)
        holder.itemView.tvDesc.text = getHtmlFromString(data?.description)
    }

    fun setData(data: List<Source>) {
        this.dataList = data
        notifyDataSetChanged()
    }

    private inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        init {
            itemView.setOnClickListener {
                callBack.onItemClicked(adapterPosition)
            }
        }
    }
}