package kg.sunrise.salamat.ui.main.profile.aboutApp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.fragment.BaseFragmentWithVM
import kg.sunrise.salamat.data.remote.response.AboutApp
import kg.sunrise.salamat.databinding.FragmentAboutAppBinding
import kg.sunrise.salamat.utils.extesions.prepareHtmlString
import kg.sunrise.salamat.utils.extesions.setStatusColor
import org.koin.android.viewmodel.ext.android.viewModel


class AboutAppFragment : BaseFragmentWithVM<FragmentAboutAppBinding , AboutAppViewModel>() {
    override val viewModel by viewModel<AboutAppViewModel>()
    override val progressBar : ConstraintLayout by lazy { binding.inclProgress.clProgress }
    private val toolbar by lazy {binding.inclToolbar}

    override fun findTypeOfObject(data: Any?) {
          when(data) {
           is AboutApp -> {
               adaptContent(data)
           }
      }
    }

    override fun successRequest() {

    }

    private fun adaptContent(dataContent : AboutApp){
        val data = prepareHtmlString(dataContent.content)
        binding.webAbout.loadDataWithBaseURL(null ,
            data , "text/html" , "UTF-8" , null)
    }


    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAboutAppBinding {
        return FragmentAboutAppBinding.inflate(inflater, container, false)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun init() {
        setUpUI()
        backgroundSet(R.drawable.preface_white_bg)
        setStatusColor(requireActivity())
        initRequest()
        setupWebView()
        listener()

    }

    private fun setUpUI() {
        toolbar.tvTitle.text = getString(R.string.about_app)
        toolbar.ivArrowBack.setImageResource(R.drawable.ic_back_pink)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView(){
        binding.webAbout.settings.javaScriptEnabled = true
        binding.webAbout.settings.builtInZoomControls = true
        binding.webAbout.settings.databaseEnabled
    }

    private fun initRequest(){
        viewModel.getAboutApp()
    }
    private fun listener(){
    toolbar.ivArrowBack.setOnClickListener {
        findNavController().popBackStack()
    }
    }
}
