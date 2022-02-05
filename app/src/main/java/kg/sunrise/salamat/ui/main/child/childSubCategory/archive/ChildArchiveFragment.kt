package kg.sunrise.salamat.ui.main.child.childSubCategory.archive

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.fragment.BaseFragmentWithVM
import kg.sunrise.salamat.data.remote.response.Archive
import kg.sunrise.salamat.data.remote.response.ChildDetailResponse
import kg.sunrise.salamat.data.remote.response.ChildParameterResponse
import kg.sunrise.salamat.databinding.FragmentChildArchiveBinding
import kg.sunrise.salamat.databinding.ToolbarWithShadowBinding
import kg.sunrise.salamat.ui.main.child.childSubCategory.archive.tabs.adapter.ArchiveTabsAdapter
import kg.sunrise.salamat.ui.main.child.childSubCategory.archive.tabs.adapter.ArchiveTabsAdapter.Companion.ARCHIVE_FRAGMENT
import kg.sunrise.salamat.ui.main.child.childSubCategory.archive.tabs.adapter.ArchiveTabsAdapter.Companion.CHILD_PARAM_FRAGMENT
import kg.sunrise.salamat.ui.main.child.childSubCategory.archive.tabs.archive.ArchiveFragment
import kg.sunrise.salamat.ui.main.child.childSubCategory.archive.tabs.childParam.ChildParamFragment
import kg.sunrise.salamat.utils.extesions.initTextView
import kg.sunrise.salamat.utils.extesions.setStatusColor
import org.koin.android.viewmodel.ext.android.viewModel


class ChildArchiveFragment :
    BaseFragmentWithVM<FragmentChildArchiveBinding, ChildArchiveViewModel>() {
    override val viewModel by viewModel<ChildArchiveViewModel>()
    override val progressBar by lazy { binding.inclProgress.clProgress }
    private val args by navArgs<ChildArchiveFragmentArgs>()
    private lateinit var toolbar : ToolbarWithShadowBinding

//    private val archiveFragment = ArchiveFragment()
//    private val childParamFragment = ChildParamFragment()

    private val tabAdapter : ArchiveTabsAdapter by lazy{
        ArchiveTabsAdapter(this@ChildArchiveFragment)
    }

    override fun findTypeOfObject(data: Any?) {
        when(data){
            is ChildDetailResponse -> {
                tabAdapter.populateArchiveData(data)
            }
            is ArrayList<*> -> {
                checkArray(data)
            }
        }
    }

    private fun checkArray(data: ArrayList<*>) {
        if (data.all { it is ChildParameterResponse }){
            tabAdapter.populateChildParamData(data as ArrayList<ChildParameterResponse>)
        }
    }

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentChildArchiveBinding {
        return FragmentChildArchiveBinding.inflate(inflater, container, false)
    }

    override fun init() {
        initToolbar()
        setUpUI()
        initRequest()
        initAdapter()
        initListeners()
    }

    private fun initListeners() {
        toolbar.ivArrowBack.setOnClickListener { findNavController().navigateUp() }
    }

    private fun initRequest() {
        viewModel.getChildDetailInfo(args.slug)
        viewModel.getChildParam(args.slug)
    }

    private fun initToolbar() {
        toolbar = binding.inclToolbar
        initTextView(toolbar.tvTitle, args.childName.trim('\"'))
        initTextView(toolbar.tvSubTitle, getString(R.string.archive))
    }

    private fun setUpUI() {
        setStatusColor(requireActivity())
    }

    private fun initAdapter(){
        binding.viewPager.isUserInputEnabled = false
        binding.viewPager.adapter = tabAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager){ tab, position ->
            tab.setText(
                when (position){
                    ARCHIVE_FRAGMENT -> R.string.archive
                    CHILD_PARAM_FRAGMENT -> R.string.child_param
                    else -> R.string.empty
                }
            )
        }.attach()
    }

    override fun successRequest() {

    }

}