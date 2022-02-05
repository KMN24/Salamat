package kg.sunrise.salamat.ui.main.home.detail.categoryItems.fragments

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import kg.sunrise.salamat.base.fragment.BaseFragment
import kg.sunrise.salamat.databinding.ParentSkillsFragmentBinding
import kg.sunrise.salamat.utils.extesions.defaultWebView
import kg.sunrise.salamat.utils.extesions.prepareHtmlString


class ParentSkillsFragment() : BaseFragment<ParentSkillsFragmentBinding>() {
    var parentResponsibilities: String = ""
    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ParentSkillsFragmentBinding {
        return ParentSkillsFragmentBinding.inflate(inflater, container, false)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun init() {
        setupWebView()
    }

    private fun setupWebView() {
        defaultWebView(binding.webParentSkills, parentResponsibilities)
    }

    fun populateData(parentResponsibilities: String) {
        val data = prepareHtmlString(parentResponsibilities)
        this.parentResponsibilities = data
    }
}
