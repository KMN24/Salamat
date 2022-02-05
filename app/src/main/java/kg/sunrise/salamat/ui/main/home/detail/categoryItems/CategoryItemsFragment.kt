package kg.sunrise.salamat.ui.main.home.detail.categoryItems

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.fragment.BaseFragmentWithVM
import kg.sunrise.salamat.data.remote.response.CategoryItemResponse
import kg.sunrise.salamat.databinding.FragmentCategoryItemsBinding
import kg.sunrise.salamat.ui.main.home.detail.HomeDetailViewModel
import kg.sunrise.salamat.ui.main.home.detail.categoryItems.RestTabsAdapter.Companion.DEVELOP_NORMS
import kg.sunrise.salamat.ui.main.home.detail.categoryItems.RestTabsAdapter.Companion.MEDICAL_SUPPORT
import kg.sunrise.salamat.ui.main.home.detail.categoryItems.RestTabsAdapter.Companion.PARENT_SKILLS
import kg.sunrise.salamat.ui.main.home.detail.categoryItems.fragments.CategoryDevelopNormsFragment
import kg.sunrise.salamat.ui.main.home.detail.categoryItems.fragments.ParentSkillsFragment
import kg.sunrise.salamat.ui.main.home.detail.categoryItems.fragments.MedicalSupportFragment
import org.koin.android.viewmodel.ext.android.viewModel


class CategoryItemsFragment :
    BaseFragmentWithVM<FragmentCategoryItemsBinding, HomeDetailViewModel>() {


    override val viewModel by viewModel<HomeDetailViewModel>()
    override val progressBar by lazy {
        binding.inclProgress.clProgress
    }
    private val args by navArgs<CategoryItemsFragmentArgs>()
    private val toolbar by lazy { binding.inclToolbar }

    private val categoryAdapter: RestTabsAdapter by lazy {
        RestTabsAdapter(this@CategoryItemsFragment)
    }


    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCategoryItemsBinding {
        return FragmentCategoryItemsBinding.inflate(inflater, container, false)
    }

    override fun init() {
        setUpUI()
        initRequests()
        initAdapter()
        statusBarColor(R.color.violet_876ead)
        navigateback()

    }

    private fun setUpUI() {
        toolbar.tvTitle.text = args.title
    }

    private fun navigateback(){
        toolbar.ivArrowBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initAdapter() {
        binding.viewpager.isUserInputEnabled = false
        binding.viewpager.adapter = categoryAdapter
        TabLayoutMediator(binding.tabs, binding.viewpager) { tab, position ->
            tab.setText(
                when (position) {
                    DEVELOP_NORMS -> R.string.Developmental_norms
                    PARENT_SKILLS -> R.string.Parent_skills
                    MEDICAL_SUPPORT -> R.string.Medical_support
                    else -> R.string.Category
                }
            )
        }.attach()
    }

    override fun findTypeOfObject(data: Any?) {
        when (data) {
            is CategoryItemResponse -> {
                fillCategoryData(data)
            }
        }
    }

    private fun fillCategoryData(data: CategoryItemResponse) {
        val categoryData = data.convertToDTO(true)
        categoryAdapter.populateData(categoryData)
        }


    private fun initRequests() {
         viewModel.getCategoriesItem(args.slug)
    }



    override fun successRequest() {

    }

}



