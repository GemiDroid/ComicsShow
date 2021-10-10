package com.gemidroid.comicshow.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gemidroid.comicshow.R
import com.gemidroid.comicshow.data.models.Comic
import kotlinx.android.synthetic.main.comic_item.view.*

class ComicsAdapter(
    private val comicsList: List<Comic>,
    private val onItemClick: (Comic) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
     val itemView = LayoutInflater.from(parent.context)
         .inflate(R.layout.comic_item, parent, false)
        return object : RecyclerView.ViewHolder(itemView){}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       val comicItem  = comicsList[position]
        holder.itemView.apply {
            setOnClickListener { onItemClick.invoke(comicItem) }
            Glide.with(context).load(comicItem.img)
                .error(R.drawable.ic_launcher_background)
                .into(img_poster)
            txt_title.text = comicItem.title
            txt_headline.text = comicItem.safeTitle
        }
    }

    override fun getItemCount() = comicsList.size
}