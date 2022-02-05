package kg.sunrise.salamat.ui.main.child.childSubCategory.fragments.ChildParameter.adapter

import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.LayoutRes
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.adapter.BaseVH
import kg.sunrise.salamat.data.remote.response.ChildParametersCharacter
import kg.sunrise.salamat.utils.extesions.initTextView

class ChildParametersVH(parent: ViewGroup, @LayoutRes id: Int) :
    BaseVH<ChildParametersCharacter>(parent, id) {
    val tvCount = itemView.findViewById<EditText>(R.id.tv_edit_number)
    val tvTitle = itemView.findViewById<TextView>(R.id.tv_title)
    val cbConfirm = itemView.findViewById<CheckBox>(R.id.cb_confirm)
    override fun bind(item: ChildParametersCharacter) {
        initTextView(tvTitle , item.title)
    }
}