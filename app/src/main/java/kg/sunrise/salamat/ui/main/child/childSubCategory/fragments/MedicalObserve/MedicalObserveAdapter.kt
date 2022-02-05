package kg.sunrise.salamat.ui.main.child.childSubCategory.fragments.MedicalObserve

import android.content.Context
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.adapter.BaseAdapter
import kg.sunrise.salamat.data.remote.response.AllCheckItemsWithNotifications
import kg.sunrise.salamat.ui.main.child.childAdd.alerts.ChooseDate
import kg.sunrise.salamat.utils.extesions.gone
import kg.sunrise.salamat.utils.extesions.visible


class MedicalObserveAdapter(private val ctx: Context) :
    BaseAdapter<AllCheckItemsWithNotifications, MedicalObserverVH>() {
    var date = ""
    var request: (Int) -> Unit = {}
    var chooseDate = ChooseDate(ctx)
    var checkBox: CheckBox? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicalObserverVH {
        val holder = MedicalObserverVH(parent, R.layout.medical_observe_vh)
        holder.itemView.setOnClickListener {
            holder.tvDate.setOnClickListener {
                chooseDate.createDatePicker(it as TextView)
                checkBox = holder.tvCheckBox
                checkBox!!.visible()
            }
        }
        holder.tvCheckBox.setOnClickListener {
            date = holder.tvDate.text.toString()
            request(holder.adapterPosition)
            holder.tvDate.gone()
            holder.tvCheckBox.gone()
            holder.iv_line.gone()
        }
        return holder
    }
}