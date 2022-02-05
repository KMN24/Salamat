package kg.sunrise.salamat.base.adapter

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import kg.sunrise.salamat.utils.extesions.inflate

abstract class BaseVH<Item>(parent: ViewGroup, @LayoutRes id: Int) : RecyclerView.ViewHolder(parent.inflate(id)){

    abstract fun bind(item : Item)
}