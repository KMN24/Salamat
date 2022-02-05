package kg.sunrise.salamat.ui.main.home.adapter

import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.adapter.BaseVH
import kg.sunrise.salamat.data.remote.response.CategoryShortResponse

class CategoryVH(parent: ViewGroup, @LayoutRes id: Int) : BaseVH<CategoryShortResponse>(parent, id) {
    private val title = itemView.findViewById<TextView>(R.id.tv_title)
    override fun bind(item: CategoryShortResponse) {
        title.text = item.title
    }
}