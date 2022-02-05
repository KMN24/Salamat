package kg.sunrise.salamat.ui.main.home.detail.categoryItems.fragments.adapter

import android.view.ViewGroup
import android.widget.TextView
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.adapter.BaseAdapter
import kg.sunrise.salamat.data.remote.response.CheckListDTO
import kg.sunrise.salamat.data.remote.response.CheckListResp

class MedicalAdapter : BaseAdapter<CheckListDTO , MedicalVH>() {
    var itemClick : (TextView) -> Unit =  {}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicalVH {
        val holder = MedicalVH(parent , R.layout.medical_support_vh)
        return holder
    }

}