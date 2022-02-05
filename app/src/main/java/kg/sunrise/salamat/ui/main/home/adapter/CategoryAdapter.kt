package kg.sunrise.salamat.ui.main.home.adapter

import android.view.ViewGroup
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.adapter.BaseAdapter
import kg.sunrise.salamat.data.remote.response.CategoryShortResponse

class CategoryAdapter : BaseAdapter<CategoryShortResponse, CategoryVH>() {
    var itemClick : (CategoryShortResponse) -> Unit = { }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryVH {
        val holder = CategoryVH(parent, R.layout.rv_item_category)
        holder.itemView.setOnClickListener { itemClick(items[holder.adapterPosition]) }
        return holder
    }
}