package kg.sunrise.salamat.ui.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.fragment.BaseFragmentWithVM
import kg.sunrise.salamat.data.remote.response.CategoryShortResponse
import kg.sunrise.salamat.databinding.HomeFragmentBinding
import kg.sunrise.salamat.databinding.ToolbarWhiteBinding
import kg.sunrise.salamat.ui.main.home.adapter.CategoryAdapter
import kg.sunrise.salamat.utils.extesions.setStatusColor
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragmentWithVM<HomeFragmentBinding, HomeViewModel>() {

    override val viewModel by viewModel<HomeViewModel>()
    private val adapter = CategoryAdapter()
    private lateinit var toolbar: ToolbarWhiteBinding

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?) =
        HomeFragmentBinding.inflate(inflater, container, false)

    override val progressBar: ConstraintLayout by lazy { binding.inclProgress.clProgress }

    override fun init() {
        setUpUI()
        initToolbar()
        initRequest()
        initAdapter()
        initListeners()

    }

    private fun initToolbar() {
        toolbar = binding.inclToolbar
        toolbar.tvTitle.text = getString(R.string.Category)
    }

    private fun initListeners() {
        binding.tvAskingQuestions.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToFreqAskedQuesFragment()
            )
        }

        binding.tvWritingDoctor.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToDoctorsAppointmentStep1Fragment()
            )
        }
    }

    private fun setUpUI() {
        backgroundSet(R.drawable.preface_bg)
        setStatusColor(requireActivity())
    }

    private fun initRequest() {
        viewModel.getShortCategories()
    }

    private fun initAdapter() {
        binding.rvCategories.adapter = adapter
        adapter.itemClick = {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToHomeDetailFragment(
                    it.title, it.slug
                )
            )
        }
    }

    override fun findTypeOfObject(data: Any?) {
        when (data) {
            is ArrayList<*> -> {
                checkArray(data)
            }
        }
    }

    private fun checkArray(data: ArrayList<*>) {
        if (data.all { it is CategoryShortResponse }) {
            adapter.setData(data as ArrayList<CategoryShortResponse>)
        }
    }

    override fun successRequest() {
    }
}