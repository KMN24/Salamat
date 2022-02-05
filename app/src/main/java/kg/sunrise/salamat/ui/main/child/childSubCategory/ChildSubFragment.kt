package kg.sunrise.salamat.ui.main.child.childSubCategory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.fragment.BaseFragmentWithVM
import kg.sunrise.salamat.databinding.FragmentChildSubBinding
import kg.sunrise.salamat.databinding.ToolbarWithShadowBinding
import kg.sunrise.salamat.utils.extesions.getBack
import org.koin.android.viewmodel.ext.android.viewModel


class ChildSubFragment : BaseFragmentWithVM<FragmentChildSubBinding, ChildSubViewModel>() {
    private val args by navArgs<ChildSubFragmentArgs>()
    override val viewModel by viewModel<ChildSubViewModel>()
    override val progressBar by lazy { binding.inclProgress.clProgress }
    private val tabAdapter by lazy {
        ChildTabsAdapter(this@ChildSubFragment)
    }
    private lateinit var toolbarWithShadowBinding: ToolbarWithShadowBinding

    override fun findTypeOfObject(data: Any?) {

    }

    override fun successRequest() {

    }

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentChildSubBinding {
        return FragmentChildSubBinding.inflate(inflater, container, false)
    }

    override fun init() {
        backgroundSet(R.drawable.preface_white_bg)
        initViewPager()
        initTabLayout()
        initToolbar()
    }

    private fun initToolbar() {
        toolbarWithShadowBinding = binding.toolbar
        toolbarWithShadowBinding.tvSubTitle.text = getString(R.string.current_child)
        toolbarWithShadowBinding.tvTitle.text = args.data.name.trim('\"')
        toolbarWithShadowBinding.ivArrowBack.setOnClickListener {
            it.getBack()
        }
    }

    private fun initViewPager() {
        binding.viewpager.isUserInputEnabled = false
        binding.viewpager.adapter = tabAdapter
        tabAdapter.sendData(args.data, args.slug!!)
    }

    private fun initTabLayout() {
        TabLayoutMediator(binding.tabs, binding.viewpager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.normOfDevelopment)
                1 -> tab.text = getString(R.string.parent_responsibilites)
                2 -> tab.text = getString(R.string.medical_observing)
                3 -> tab.text = getString(R.string.child_parameterss)
            }
        }.attach()

    }

}