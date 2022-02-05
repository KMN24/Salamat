package kg.sunrise.salamat.ui.auth.region

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.fragment.BaseFragmentWithVM
import kg.sunrise.salamat.data.remote.response.RegionResponse
import kg.sunrise.salamat.databinding.RegionFragmentBinding
import kg.sunrise.salamat.ui.auth.region.bottom.RegionBottomFragment
import kg.sunrise.salamat.utils.preference.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class RegionFragment : BaseFragmentWithVM<RegionFragmentBinding, RegionViewModel>() {
    override val viewModel by viewModel<RegionViewModel>()

    override val progressBar by lazy { binding.inclProgress.clProgress }


    private var region: String? = null

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): RegionFragmentBinding {
        return RegionFragmentBinding.inflate(inflater, container, false)
    }

    override fun init() {
        backgroundSet(R.drawable.preface_bg)
        initListener()
    }

    private fun initListener() {
        binding.clChooseRegion.setOnClickListener {
            val fragment = RegionBottomFragment()
            fragment.itemClick = {
                region = it.value
                binding.tvTitle.text = it.uiValue
                binding.btnNext.isEnabled = region != null
            }
            fragment.show(childFragmentManager, fragment.tag)
        }
        binding.btnNext.setOnClickListener {
            if (getPhone(requireContext()) != EMPTY_VALUE && getUid(requireContext()) != EMPTY_VALUE) {
                viewModel.setRegion(
                    getPhone(requireContext()),
                    getRegistraturaToken(requireContext()),
                    region!!
                )
            }
        }
    }


    override fun findTypeOfObject(data: Any?) {
        when (data) {
            is RegionResponse -> {
                setAccessToken(requireContext(), data.token)
                binding.root.findNavController()
                    .navigate(RegionFragmentDirections.actionRegionFragmentToDownLoadFragment())
            }
        }
    }

    override fun successRequest() {

    }
}