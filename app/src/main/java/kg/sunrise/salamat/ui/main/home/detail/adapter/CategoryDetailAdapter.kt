package kg.sunrise.salamat.ui.main.home.detail.adapter

import android.view.ViewGroup
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.adapter.BaseAdapter
import kg.sunrise.salamat.data.remote.response.SubCarResp

class CategoryDetailAdapter :
    BaseAdapter<SubCarResp, CategoryDetailVH>() {
    var itemClick: (SubCarResp) -> Unit = { }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryDetailVH {
        val holder = CategoryDetailVH(parent, R.layout.rv_item_category)
        holder.itemView.setOnClickListener { itemClick(items[holder.adapterPosition]) }
        return holder
    }
}