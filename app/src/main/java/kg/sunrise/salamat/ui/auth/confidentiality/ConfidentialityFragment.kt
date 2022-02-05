package kg.sunrise.salamat.ui.auth.confidentiality

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.fragment.BaseFragment
import kg.sunrise.salamat.databinding.ConfidentialityFragmentBinding
import kg.sunrise.salamat.databinding.ToolbarWithShadowBinding
import kg.sunrise.salamat.utils.extesions.setStatusColor
import timber.log.Timber

class ConfidentialityFragment : BaseFragment<ConfidentialityFragmentBinding>() {

    private lateinit var toolbar: ToolbarWithShadowBinding

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ConfidentialityFragmentBinding {
        return ConfidentialityFragmentBinding.inflate(inflater, container, false)
    }

    override fun init() {
        setUpUI()
        initToolbar()
        initWebView()
        initListener()
    }

    private fun setUpUI() {
        setStatusColor(requireActivity())
    }

    private fun initToolbar() {
        toolbar = binding.inclToolbar
        toolbar.tvTitle.text = getString(R.string.Confidentiality_condition)
    }

    private fun initListener() {
        toolbar.ivArrowBack.setOnClickListener {
            it.findNavController().popBackStack()
        }
    }

    private fun initWebView() {
        val html = StringBuilder("<h1 align=\"center\">${getString(R.string.Confidentiality_html_title)}</h1><br/><br/><br/>")
        html.append(getString(R.string.Confidentiality_html_text1) + "<br/><br/><br/>")
        html.append(getString(R.string.Confidentiality_html_text2))
        Timber.e(html.toString())
        binding.webAbout.settings.builtInZoomControls = true
        binding.webAbout.loadDataWithBaseURL(
            null,
            html.toString(),
            "text/html",
            "UTF-8",
            null
        )
    }
}