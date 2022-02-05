package kg.sunrise.salamat.ui.main.home.doctors_appointment.step2.adapter

import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.adapter.BaseVH
import kg.sunrise.salamat.data.remote.response.DoctorsResponse

class DoctorsVH( parent: ViewGroup, @LayoutRes id: Int): BaseVH<DoctorsResponse>(parent, id) {

    private val doctorFio = itemView.findViewById<TextView>(R.id.tv_doctors_full_name)
    private val speciality = itemView.findViewById<TextView>(R.id.tv_specialty)
    private val institutionName = itemView.findViewById<TextView>(R.id.tv_institution_name)

    override fun bind(item: DoctorsResponse) {
        doctorFio.text = item.fullName
        speciality.text = item.speciality
        institutionName.text = item.institutionName
    }
}