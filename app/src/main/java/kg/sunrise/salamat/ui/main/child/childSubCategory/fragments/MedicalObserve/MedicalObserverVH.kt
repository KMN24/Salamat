package kg.sunrise.salamat.ui.main.child.childSubCategory.fragments.MedicalObserve

import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.adapter.BaseVH
import kg.sunrise.salamat.data.remote.response.AllCheckItemsWithNotifications
import kg.sunrise.salamat.utils.extesions.initTextView

class MedicalObserverVH(parent: ViewGroup, @LayoutRes id: Int) :
    BaseVH<AllCheckItemsWithNotifications>(parent, id) {
    val tvTitle = itemView.findViewById<TextView>(R.id.tv_title)
    val tvDesc = itemView.findViewById<TextView>(R.id.tv_desc)
    val tvDate = itemView.findViewById<TextView>(R.id.tv_date)
    val tvCheckBox = itemView.findViewById<CheckBox>(R.id.cb_date)
    val iv_line = itemView.findViewById<ImageView>(R.id.iv_line)
    override fun bind(item: AllCheckItemsWithNotifications) {
        item.convertDTO(true)
        initTextView(tvTitle , item.titleRu)
        initTextView(tvDesc , item.descriptionRu)
    }
}