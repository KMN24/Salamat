package kg.sunrise.salamat.ui.main.child.childSubCategory.archive.tabs.childParam

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import kg.sunrise.salamat.base.fragment.BaseFragmentWithVM
import kg.sunrise.salamat.data.remote.response.ChildParameterDTO
import kg.sunrise.salamat.data.remote.response.ChildParameterResponse
import kg.sunrise.salamat.databinding.FragmentTabChildParamBinding
import kg.sunrise.salamat.ui.main.child.childSubCategory.archive.tabs.childParam.adapter.ChildParamAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class ChildParamFragment: BaseFragmentWithVM<FragmentTabChildParamBinding, ChildParamViewModel>() {
    override val viewModel by viewModel<ChildParamViewModel>()

    override val progressBar: ConstraintLayout by lazy { binding.inclProgress.clProgress }
    private val adapter = ChildParamAdapter()

    override fun findTypeOfObject(data: Any?) {
    }

    override fun successRequest() {
    }

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTabChildParamBinding {
        return FragmentTabChildParamBinding.inflate(inflater)
    }

    override fun init() {
        initAdapter()
    }

    private fun initAdapter() {
        binding.rvChildParam.adapter = adapter
    }

    fun populateData(data: ArrayList<ChildParameterDTO>){
        adapter.setData(data)
    }
}