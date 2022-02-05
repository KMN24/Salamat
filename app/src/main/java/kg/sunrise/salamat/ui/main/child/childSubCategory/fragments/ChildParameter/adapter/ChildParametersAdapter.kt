package kg.sunrise.salamat.ui.main.child.childSubCategory.fragments.ChildParameter.adapter


import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.adapter.BaseAdapter
import kg.sunrise.salamat.data.remote.response.ChildParametersCharacter

class ChildParametersAdapter : BaseAdapter<ChildParametersCharacter, ChildParametersVH>() {
    var itemClick: (Int , EditText) -> Unit = { _: Int, _: EditText -> }
    var tvCount: EditText? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildParametersVH {
        val holder = ChildParametersVH(parent, R.layout.child_parameters_vh)
        holder.cbConfirm.isEnabled = false
        holder.tvCount.doAfterTextChanged {
            holder.cbConfirm.isEnabled = true
        }
        holder.cbConfirm.setOnClickListener {
            itemClick(holder.adapterPosition , holder.tvCount)
        }
        return holder
    }
}