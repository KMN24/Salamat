package kg.sunrise.salamat.ui.main.child.adapter

import android.view.ViewGroup
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.adapter.BaseAdapter
import kg.sunrise.salamat.data.remote.response.ChildResponse

class ChildAdapter :
    BaseAdapter<ChildResponse, ChildVH>() {
    var itemClick: (ChildResponse) -> Unit = { }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildVH {
        val holder = ChildVH(parent, R.layout.rv_item_child)
        holder.itemView.setOnClickListener { itemClick(items[holder.adapterPosition]) }
        return holder
    }
}