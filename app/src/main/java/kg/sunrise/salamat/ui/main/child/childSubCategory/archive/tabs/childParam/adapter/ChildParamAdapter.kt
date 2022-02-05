package kg.sunrise.salamat.ui.main.child.childSubCategory.archive.tabs.childParam.adapter

import android.view.ViewGroup
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.adapter.BaseAdapter
import kg.sunrise.salamat.data.remote.response.ChildParameterDTO
import kg.sunrise.salamat.data.remote.response.ChildParameterResponse


class ChildParamAdapter: BaseAdapter<ChildParameterDTO, ChildParamVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildParamVH {
        return ChildParamVH(parent, R.layout.rv_item_child_param)
    }
}