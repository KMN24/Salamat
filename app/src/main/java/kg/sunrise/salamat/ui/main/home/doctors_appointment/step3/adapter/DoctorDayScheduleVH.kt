package kg.sunrise.salamat.ui.main.home.doctors_appointment.step3.adapter

import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.ConstraintLayout
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.adapter.BaseVH
import kg.sunrise.salamat.data.remote.response.DoctorDayResponse
import kg.sunrise.salamat.utils.extesions.datePattern.DateTimeParser
import kg.sunrise.salamat.utils.extesions.datePattern.DateTimePattern

class DoctorDayScheduleVH(parent: ViewGroup, @LayoutRes id: Int): BaseVH<DoctorDayResponse>(parent, id) {

    private val time = itemView.findViewById<TextView>(R.id.tv_time)
    private val root = itemView.findViewById<ConstraintLayout>(R.id.cl_root)


    override fun bind(item: DoctorDayResponse) {
        val timeParser = DateTimeParser.formatDateTime(item.datetime, DateTimePattern.yyyy_MM_dd_HH_mm, DateTimePattern.HH_mm)
        time.text = timeParser

        when {
            item.isAvailable -> {
                time.setBackgroundResource(R.drawable.shape_all_12dp_blue_6294f3)
                time.isEnabled = true
            }
            item.appointment != null && !item.isAvailable -> {
                time.setBackgroundResource(R.drawable.shape_all_12dp_red_e02523)
                time.isEnabled = true
            }
            !item.isAvailable -> {
                time.setBackgroundResource(R.drawable.shape_all_12dp_grey_c4c4c4)
                root.isEnabled = false
            }
        }
    }

}