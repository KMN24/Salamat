package kg.sunrise.salamat.ui.main.child

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.fragment.BaseFragmentWithVM
import kg.sunrise.salamat.data.remote.response.ChildResponse
import kg.sunrise.salamat.databinding.ChildFragmentBinding
import kg.sunrise.salamat.databinding.ToolbarWhiteBinding
import kg.sunrise.salamat.ui.main.child.adapter.ChildAdapter
import kg.sunrise.salamat.utils.extesions.setStatusColor
import org.koin.android.viewmodel.ext.android.viewModel

class ChildFragment : BaseFragmentWithVM<ChildFragmentBinding, ChildViewModel>() {

    private val adapter = ChildAdapter()
    private lateinit var toolbar : ToolbarWhiteBinding

    override val viewModel by viewModel<ChildViewModel>()
    override val progressBar by lazy {
        binding.inclProgress.clProgress
    }

    override fun init() {
        initToolbar()
        setStatusColor(requireActivity())
        backgroundSet(R.drawable.preface_bg)
        initAdapter()
        listeners()
        viewModel.getChildList()
    }

    private fun initToolbar() {
        toolbar = binding.inclToolbar
        toolbar.tvTitle.text = getString(R.string.child_acc)
    }

    override fun findTypeOfObject(data: Any?) {
        when (data) {
            is ArrayList<*> -> {
                checkArray(data)
            }
        }
    }

    private fun initAdapter() {
        binding.rvChild.adapter = adapter
        adapter.itemClick = {
            findNavController().navigate(ChildFragmentDirections.actionChildFragmentToChildDetailFragment(it.slug))
        }
    }

    override fun successRequest() {

    }

    private fun checkArray(data: ArrayList<*>) {
        if (data.all { it is ChildResponse }) {
            adapter.setData(data as ArrayList<ChildResponse>)
        }
    }

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ChildFragmentBinding {
        return ChildFragmentBinding.inflate(inflater, container, false)
    }

    private fun listeners() {
        binding.linearChildAdd.setOnClickListener {
            findNavController().navigate(ChildFragmentDirections.actionChildFragmentToChildAddFragment())
        }
    }

}