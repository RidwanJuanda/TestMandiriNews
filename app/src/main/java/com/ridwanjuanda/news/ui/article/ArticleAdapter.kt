package com.ridwanjuanda.news.ui.article

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ridwanjuanda.news.R
import com.ridwanjuanda.news.models.Article
import com.ridwanjuanda.news.utils.AdapterListener
import com.ridwanjuanda.news.utils.getHtmlFromString
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_article.view.*

/**
 * @author Ridwan Juanda
 * @date 05/10/20
 */

class ArticleAdapter internal constructor(private var dataList: List<Article>?, val callBack: AdapterListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_article, viewGroup, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList?.size ?: 0
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val holder = viewHolder as ViewHolder
        val data = dataList?.get(position)
        holder.itemView.tvTitle.text = getHtmlFromString(data?.title)
        holder.itemView.tvSource.text = data?.source?.name ?: "-"
        holder.itemView.tvDesc.text = getHtmlFromString(data?.description)
        if (data?.urlToImage != null && data.urlToImage != "") {
            Picasso.get().load(data.urlToImage).fit().centerCrop()
                .into(holder.itemView.ivThumb)
        }
    }

    fun setData(data: List<Article>) {
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