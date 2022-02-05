package kg.sunrise.salamat.ui.main.child.childSubCategory.archive.tabs.archive.adapter

import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.adapter.BaseVH
import kg.sunrise.salamat.data.remote.response.ArchiveDto
import kg.sunrise.salamat.utils.extesions.initTextView

class ArchiveVH(parent: ViewGroup, @LayoutRes id : Int): BaseVH<ArchiveDto>(parent, id) {

    private val title = itemView.findViewById<TextView>(R.id.tv_item_title)
    private val description = itemView.findViewById<TextView>(R.id.tv_description)
    private val date = itemView.findViewById<TextView>(R.id.tv_date)

    override fun bind(item: ArchiveDto) {
        initTextView(title, item.checkListItemDto.title)
        initTextView(description, item.checkListItemDto.description)
        initTextView(date, item.checkedDate)
    }
}