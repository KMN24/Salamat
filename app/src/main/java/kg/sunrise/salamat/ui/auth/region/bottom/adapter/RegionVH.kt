package kg.sunrise.salamat.ui.auth.region.bottom.adapter

import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.adapter.BaseVH
import kg.sunrise.salamat.data.helper.RegionDataHelper

class RegionVH(parent: ViewGroup, @LayoutRes id: Int) : BaseVH<RegionDataHelper>(parent, id) {
    private val tv = itemView.findViewById<TextView>(R.id.tv_title)

    override fun bind(item: RegionDataHelper) {
        tv.text = item.uiValue
    }
}