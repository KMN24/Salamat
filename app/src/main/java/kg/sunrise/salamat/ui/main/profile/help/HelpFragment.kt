package kg.sunrise.salamat.ui.main.profile.help

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.fragment.BaseFragmentWithVM
import kg.sunrise.salamat.data.remote.response.HelpResponse
import kg.sunrise.salamat.databinding.FragmentHelpBinding
import kg.sunrise.salamat.utils.extesions.setStatusColor
import org.koin.android.viewmodel.ext.android.viewModel

class HelpFragment : BaseFragmentWithVM<FragmentHelpBinding, HelpViewModel>() {
    override val viewModel by viewModel<HelpViewModel>()
    override val progressBar by lazy { binding.inclProgress.clProgress }
    private val toolbar by lazy { binding.inclToolbar }

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?): FragmentHelpBinding {
        return FragmentHelpBinding.inflate(inflater, container, false)
    }


    override fun init() {
        setUpUI()
        initRequest()
        initWebView()
        initListeners()

    }

    private fun initListeners() {
        toolbar.ivArrowBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.builtInZoomControls = true
        binding.webView.settings.databaseEnabled
    }

    private fun initRequest() {
        viewModel.getHelp()
    }

    private fun setUpUI() {
        setStatusColor(requireActivity())
        backgroundSet(R.drawable.preface_white_bg)
        toolbar.tvTitle.text = getString(R.string.help)
        toolbar.ivArrowBack.setImageResource(R.drawable.ic_back_pink)
    }

    override fun findTypeOfObject(data: Any?) {
        when(data){
            is HelpResponse -> showPdf(data)
        }
    }

    private fun showPdf(pdf : HelpResponse){
        val url = "https://drive.google.com/viewerng/viewer?embedded=true&url=" + pdf.file
        binding.webView.loadUrl(url)
    }

    override fun successRequest() {
    }


}