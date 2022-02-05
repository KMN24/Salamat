package kg.sunrise.salamat.ui.main.home.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.fragment.BaseFragmentWithVM
import kg.sunrise.salamat.data.remote.response.CategoryFullResponse
import kg.sunrise.salamat.data.remote.response.SubCarResp
import kg.sunrise.salamat.databinding.HomeDetailFragmentBinding
import kg.sunrise.salamat.ui.main.home.detail.adapter.CategoryDetailAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class HomeDetailFragment() : BaseFragmentWithVM<HomeDetailFragmentBinding, HomeDetailViewModel>() {
    private val args by navArgs<HomeDetailFragmentArgs>()
    override val viewModel by viewModel<HomeDetailViewModel>()
    override val progressBar by lazy {
        binding.inclProgress.clProgress
    }
    var title = ""
    private var slug = ""
    private val adapter = CategoryDetailAdapter()

    override fun findTypeOfObject(data: Any?) {
        when (data) {
            is CategoryFullResponse -> {
                slug = data.slug
                checkData(data.subcategories, data)
            }
        }
    }

    override fun successRequest() {

    }

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): HomeDetailFragmentBinding {
        return HomeDetailFragmentBinding.inflate(inflater, container, false)
    }

    override fun init() {
        backgroundSet(R.drawable.preface_bg)
        binding.tvTitle.text = args.categoryName
        initRequest()
        navigateBack()
        initAdapter()
        navigateToTest()
    }
    private fun navigateBack() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initRequest() {
        viewModel.getFullCategories(args.categoryId ?: "")
    }

    private fun initAdapter() {
        binding.rvSubcategories.adapter = adapter
        adapter.itemClick = {
            findNavController().navigate(
                HomeDetailFragmentDirections.actionHomeDetailFragmentToCategoryItemsFragment(
                    it.titleRu, it.slug
                )
            )
        }
    }

    private fun navigateToTest() {
        binding.btnTest.setOnClickListener {
            findNavController().navigate(
                HomeDetailFragmentDirections.actionHomeDetailFragmentToTestMainFragment(
                    slug , title
                )
            )
        }
    }

    private fun checkData(subcategories: ArrayList<*>, data: CategoryFullResponse) {
        if (subcategories.any { it is SubCarResp }) {
            data.convertDTO(true)
            title = data.titleRu
            adapter.setData(subcategories as ArrayList<SubCarResp>)
        }
        initView(data)
    }

    private fun initView(data: CategoryFullResponse) {
        binding.tvTitle.text = args.categoryName
        binding.tvDetail.text = data.descriptionRu
        Glide.with(this).load(data.image).into(binding.ivDetalizationPhoto)
    }
}