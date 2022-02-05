package kg.sunrise.salamat.ui.auth.region.bottom

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.fragment.BaseBottomFragment
import kg.sunrise.salamat.data.helper.RegionDataHelper
import kg.sunrise.salamat.databinding.RegionBottomFragmentBinding
import kg.sunrise.salamat.ui.auth.region.bottom.adapter.RegionAdapter
import kg.sunrise.salamat.ui.main.profile.changeCity.ChangeRegionFragment

class RegionBottomFragment : BaseBottomFragment<RegionBottomFragmentBinding>() {
    private val adapter = RegionAdapter()
    var itemClick: (RegionDataHelper) -> Unit = { }

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): RegionBottomFragmentBinding {
        return RegionBottomFragmentBinding.inflate(inflater, container, false)
    }

    override fun init() {
        isCancelable = false
        setUpUI()
        initAdapter()
        initListener()
    }

    private fun setUpUI() {
        backgroundSet(R.drawable.shape_top_radius_10)
        when (parentFragment) {
            is RegionBottomFragment -> changeTitle(getString(R.string.toChoose_region))
            is ChangeRegionFragment -> changeTitle(getString(R.string.change_region))
        }
    }

    private fun initListener() {
        adapter.clickItem = {
            this.itemClick(it)
            dismiss()
        }
        binding.ivClose.setOnClickListener {
            dismiss()
            popBackStack()
        }
    }

    private fun initAdapter() {
        val array = getArrayData()
        adapter.setData(array)
        binding.rvRegions.isNestedScrollingEnabled = true
        binding.rvRegions.adapter = adapter
    }

    private fun getArrayData() = ArrayList<RegionDataHelper>().also { list ->
        resources.getTextArray(R.array.region_data).forEach {
            val split = it.split(":")
            list.add(RegionDataHelper(split[0], split[1]))
        }
    }

    private fun changeTitle(title: String) {
        binding.tvTitle.text = title
    }

    private fun popBackStack() {
        if (parentFragment is ChangeRegionFragment) {
            findNavController().popBackStack()
        }
    }
}