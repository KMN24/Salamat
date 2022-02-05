package kg.sunrise.salamat.ui.auth.region.bottom.adapter

import android.view.ViewGroup
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.adapter.BaseAdapter
import kg.sunrise.salamat.data.helper.RegionDataHelper

class RegionAdapter : BaseAdapter<RegionDataHelper, RegionVH>() {
    var clickItem : (RegionDataHelper) -> Unit = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegionVH {
        val holder = RegionVH(parent, R.layout.rv_item_region)
        holder.itemView.setOnClickListener { clickItem(items[holder.adapterPosition]) }
        return holder
    }
}