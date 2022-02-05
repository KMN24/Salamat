package kg.sunrise.salamat.ui.main.home.doctors_appointment.step3.adapter

import android.view.View
import android.view.ViewGroup
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.adapter.BaseAdapter
import kg.sunrise.salamat.data.remote.response.CategoryShortResponse
import kg.sunrise.salamat.data.remote.response.DoctorDayResponse
import kg.sunrise.salamat.utils.extesions.visible
import kotlinx.android.synthetic.main.fragment_doctors_appointment_step3.view.*
import kotlinx.android.synthetic.main.rv_item_doctor_schedule.view.*
import timber.log.Timber

class DoctorDayScheduleAdapter : BaseAdapter<DoctorDayResponse, DoctorDayScheduleVH>() {
    var itemClick: (DoctorDayResponse) -> Unit = { }
    var prevItem: View? = null
    var hasPrevItemAppoint = false
    var currentItem : View? = null
    var currentPosition : Int ?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorDayScheduleVH {
        val holder = DoctorDayScheduleVH(parent, R.layout.rv_item_doctor_schedule)

        holder.itemView.setOnClickListener {

            currentPosition = holder.adapterPosition
            currentItem = holder.itemView.tv_time
            itemClick(items[holder.adapterPosition])
            prevItem = holder.itemView.tv_time

        }
        return holder
    }

}