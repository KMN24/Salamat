package kg.sunrise.salamat.ui.main.child.childSubCategory.archive.tabs.childParam.adapter

import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.adapter.BaseVH
import kg.sunrise.salamat.data.remote.response.ChildParameterDTO
import kg.sunrise.salamat.utils.extesions.initTextView

class ChildParamVH(parent: ViewGroup, @LayoutRes id: Int) : BaseVH<ChildParameterDTO>(parent, id) {

   private val title = itemView.findViewById<TextView>(R.id.tv_title)
    private val value = itemView.findViewById<TextView>(R.id.tv_value)

    override fun bind(item: ChildParameterDTO) {
        initTextView(title, item.key)
        initTextView(value, item.value)
    }
}