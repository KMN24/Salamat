package kg.sunrise.salamat.ui.main.home.doctors_appointment.step2.adapter


import android.view.ViewGroup
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.adapter.BaseAdapter
import kg.sunrise.salamat.data.remote.response.DoctorsResponse

class DoctorsAdapter : BaseAdapter<DoctorsResponse, DoctorsVH>() {
    var itemClick: (DoctorsResponse) -> Unit = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorsVH {
        val holder = DoctorsVH(parent, R.layout.rv_item_doctor)
        holder.itemView.setOnClickListener { itemClick(items[holder.adapterPosition]) }
        return holder
    }

}