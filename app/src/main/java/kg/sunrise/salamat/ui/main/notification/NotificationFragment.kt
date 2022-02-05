package kg.sunrise.salamat.ui.main.notification

import android.view.LayoutInflater
import android.view.ViewGroup
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.fragment.BaseFragmentWithVM
import kg.sunrise.salamat.databinding.NotificationFragmentBinding
import kg.sunrise.salamat.databinding.ToolbarWhiteBinding
import kg.sunrise.salamat.utils.extesions.setStatusColor
import org.koin.android.viewmodel.ext.android.viewModel

class NotificationFragment :
    BaseFragmentWithVM<NotificationFragmentBinding, NotificationViewModel>() {

    override val viewModel by viewModel<NotificationViewModel>()
    override val progressBar by lazy { binding.inclProgress.clProgress }
    private lateinit var toolbar: ToolbarWhiteBinding

    override fun init() {
        initToolbar()
        setUpUI()
    }

    private fun initToolbar() {
        toolbar = binding.inclToolbar
        toolbar.tvTitle.text = getString(R.string.notification)
    }

    private fun setUpUI() {
        backgroundSet(R.drawable.preface_bg)
        setStatusColor(requireActivity())
    }

    override fun findTypeOfObject(data: Any?) {

    }

    override fun successRequest() {

    }

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): NotificationFragmentBinding {
        return NotificationFragmentBinding.inflate(inflater, container, false)
    }


}