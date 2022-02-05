package kg.sunrise.salamat.ui.main.child.childSubCategory.archive.tabs.archive.adapter

import android.view.ViewGroup
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.adapter.BaseAdapter
import kg.sunrise.salamat.data.remote.response.ArchiveDto

class ArchiveAdapter: BaseAdapter<ArchiveDto, ArchiveVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArchiveVH {
        return ArchiveVH(parent, R.layout.rv_item_archive)
    }

}