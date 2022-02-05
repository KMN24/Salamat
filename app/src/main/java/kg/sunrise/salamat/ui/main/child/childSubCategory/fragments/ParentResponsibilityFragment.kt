package kg.sunrise.salamat.ui.main.child.childSubCategory.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import kg.sunrise.salamat.base.fragment.BaseFragment
import kg.sunrise.salamat.databinding.ParentResponsibilitiesBinding
import kg.sunrise.salamat.utils.extesions.defaultWebView
import kg.sunrise.salamat.utils.extesions.prepareHtmlString


class ParentResponsibilityFragment : BaseFragment<ParentResponsibilitiesBinding>() {
    private var data = ""
    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ParentResponsibilitiesBinding {
        return ParentResponsibilitiesBinding.inflate(inflater)
    }

    override fun init() {
        initWebView()
    }

    private fun initWebView() {
        defaultWebView(binding.wbParentResponsibilites, data)
    }

    fun populateData(data: String) {
        val finalString = prepareHtmlString(data)
        this.data = finalString
    }


}