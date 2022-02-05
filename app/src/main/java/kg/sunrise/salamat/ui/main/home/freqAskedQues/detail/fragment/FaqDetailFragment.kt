package kg.sunrise.salamat.ui.main.home.freqAskedQues.detail.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kg.sunrise.salamat.R
import org.koin.android.viewmodel.ext.android.viewModel
import kg.sunrise.salamat.base.fragment.BaseFragmentWithVM
import kg.sunrise.salamat.data.remote.response.FreqAskedQuesDetailResponse
import kg.sunrise.salamat.databinding.FragmentFreqAskedQuesDetailBinding
import kg.sunrise.salamat.utils.extesions.prepareHtmlString

class FaqDetailFragment : BaseFragmentWithVM<FragmentFreqAskedQuesDetailBinding, FaqDetailViewModel>(){
    private val args by navArgs<FaqDetailFragmentArgs>()
    override val viewModel by viewModel<FaqDetailViewModel>()
    override val progressBar by lazy { binding.inclProgress.clProgress }
    private val toolbar by lazy { binding.inclToolbar }

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFreqAskedQuesDetailBinding {
        return FragmentFreqAskedQuesDetailBinding.inflate(inflater, container, false)
    }

    override fun init() {
        setUpUI()
        initRequest()
        setUpWebView()
        initListeners()
    }

    private fun setUpUI() {
        toolbar.root.background = null
    }

    private fun initRequest() {
        viewModel.getFeedbackDetail(args.id ?: 0)
    }

    private fun setUpWebView(){
        binding.webView.settings.apply {
            javaScriptEnabled = true
            builtInZoomControls = true
            databaseEnabled
        }
    }

    private fun initListeners(){
        toolbar.ivArrowBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun findTypeOfObject(data: Any?) {
        when (data) {
            is FreqAskedQuesDetailResponse -> {
                show(data.issue, data.review)
            }
        }
    }


    private fun show(issue: String, review: String) {
        val dataWeb = prepareHtmlString(review)

        binding.tvQuestion.text = issue

        binding.webView.loadDataWithBaseURL(
            null,
            dataWeb,
            "text/html",
            "UTF-8", null
        )
    }

    override fun successRequest() {
    }


}