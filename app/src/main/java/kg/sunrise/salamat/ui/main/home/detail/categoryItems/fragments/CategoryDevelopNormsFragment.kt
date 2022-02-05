package kg.sunrise.salamat.ui.main.home.detail.categoryItems.fragments


import android.view.LayoutInflater
import android.view.ViewGroup
import kg.sunrise.salamat.base.fragment.BaseFragment
import kg.sunrise.salamat.databinding.FragmentCategoryDevelopNormsBinding
import kg.sunrise.salamat.utils.extesions.defaultWebView

class CategoryDevelopNormsFragment : BaseFragment<FragmentCategoryDevelopNormsBinding>() {

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCategoryDevelopNormsBinding {
        return FragmentCategoryDevelopNormsBinding.inflate(inflater, container, false)
    }

    override fun init() {

    }

    fun populateData(doctorSupervision: String) {
        defaultWebView(binding.webFirst, doctorSupervision)
    }


}