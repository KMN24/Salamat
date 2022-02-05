package kg.sunrise.salamat.ui.main.profile.changeCity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.fragment.BaseFragmentWithVM
import kg.sunrise.salamat.databinding.FragmentChangeCityBinding
import kg.sunrise.salamat.ui.auth.region.bottom.RegionBottomFragment
import org.koin.android.viewmodel.ext.android.viewModel

class ChangeRegionFragment: BaseFragmentWithVM<FragmentChangeCityBinding, ChangeRegionViewModel>() {
    override val viewModel by viewModel<ChangeRegionViewModel>()
    override val progressBar by lazy { binding.inclProgress.clProgress }
    private var region :String ?= null

    override fun init() {
        changeRegion()
    }

    private fun initRequest() {
        region?.let {
            viewModel.changeRegion(region!!)
        }
    }

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentChangeCityBinding {
        return FragmentChangeCityBinding.inflate(inflater, container, false)
    }

    override fun findTypeOfObject(data: Any?) {
    }

    private fun changeRegion() {
        val fragment = RegionBottomFragment()
        fragment.itemClick = {
            region = it.value
            initRequest()
        }
        fragment.show(childFragmentManager, fragment.tag)
    }

    private fun returnToProfile() {
        findNavController().navigateUp()
    }

    override fun successRequest() {
        toast(getString(R.string.region_successfully_changed))
        returnToProfile()
    }

}