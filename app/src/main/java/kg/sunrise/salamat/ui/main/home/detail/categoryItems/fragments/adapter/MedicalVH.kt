package kg.sunrise.salamat.ui.main.home.detail.categoryItems.fragments.adapter

import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.adapter.BaseVH
import kg.sunrise.salamat.data.remote.response.CheckListDTO
import kg.sunrise.salamat.data.remote.response.CheckListResp
import kg.sunrise.salamat.databinding.MedicalSupportBinding

class MedicalVH(parent: ViewGroup , @LayoutRes id : Int) : BaseVH<CheckListDTO>(parent , id) {
    private val textTitle = itemView.findViewById<TextView>(R.id.medical_title)
    private val textDescription = itemView.findViewById<TextView>(R.id.medical_description)
    override fun bind(item: CheckListDTO) {
          textTitle.text = item.title
          textDescription.text = item.description
    }
}