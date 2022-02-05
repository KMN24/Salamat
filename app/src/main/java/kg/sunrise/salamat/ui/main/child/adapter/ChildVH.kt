package kg.sunrise.salamat.ui.main.child.adapter

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.bumptech.glide.Glide
import kg.sunrise.salamat.App
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.adapter.BaseVH
import kg.sunrise.salamat.data.remote.response.ChildResponse

class ChildVH(parent: ViewGroup, @LayoutRes id: Int) : BaseVH<ChildResponse>(parent, id) {
    private val title = itemView.findViewById<TextView>(R.id.textChildAdd)
    private val image = itemView.findViewById<ImageView>(R.id.imageChild)
    override fun bind(item: ChildResponse) {
        val result: String = item.name.replace("\"", "")
        title.text = result
       Glide.with(App.context)
            .load(item.avatar)
            .into(image)
    }
}