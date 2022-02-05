package kg.sunrise.salamat.ui.main.child.childSubCategory.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import kg.sunrise.salamat.base.fragment.BaseFragment
import kg.sunrise.salamat.databinding.NormsDevelopmentBinding
import kg.sunrise.salamat.utils.extesions.defaultWebView


class NormsInDevolopmentFragment : BaseFragment<NormsDevelopmentBinding>() {
    var data = ""
    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): NormsDevelopmentBinding {
        return NormsDevelopmentBinding.inflate(inflater)
    }

    override fun init() {
        initWebView()
    }

    private fun initWebView() {
        defaultWebView(binding.wbNormsOfDevelopment, data)
    }

    fun populateData(data: String) {
        this.data = data
    }
}