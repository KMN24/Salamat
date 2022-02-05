package kg.sunrise.salamat.ui.main.home.freqAskedQues

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.fragment.BaseFragmentWithVM
import kg.sunrise.salamat.data.remote.response.FreqAskedQuesResponse
import kg.sunrise.salamat.databinding.FragmentFreqAskedQuesBinding
import kg.sunrise.salamat.databinding.ToolbarWithShadowBinding
import kg.sunrise.salamat.ui.main.home.freqAskedQues.adapter.FAQAdapter
import kg.sunrise.salamat.utils.extesions.hideKeyboard
import kg.sunrise.salamat.utils.extesions.setStatusColor
import org.koin.android.viewmodel.ext.android.viewModel


class FreqAskedQuesFragment() :
    BaseFragmentWithVM<FragmentFreqAskedQuesBinding, FreqAskedQuesViewModel>() {
    override val viewModel by viewModel<FreqAskedQuesViewModel>()
    override val progressBar: ConstraintLayout by lazy { binding.inclProgress.clProgress }
    private lateinit var toolbar : ToolbarWithShadowBinding

    private val adapter = FAQAdapter()
    private var searchArrayList: ArrayList<FreqAskedQuesResponse> = ArrayList()
    private var tempArrayList: ArrayList<FreqAskedQuesResponse> = ArrayList()

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFreqAskedQuesBinding {
        return FragmentFreqAskedQuesBinding.inflate(inflater, container, false)
    }

    override fun init() {
        initToolbar()
        setUpUI()
        initAdapter()
        initListeners()
        initRequest()
    }

    private fun initToolbar() {
        toolbar = binding.inclToolbar
    }

    private fun setUpUI() {
        Glide.with(this).load(R.drawable.ic_search).into(binding.ivSearch)
        toolbar.tvTitle.text = getString(R.string.constantly_asking_questions)
        setStatusColor(requireActivity())
    }

    private fun initRequest() {
        viewModel.getFeedback()
    }

    private fun initListeners() {
        toolbar.ivArrowBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.ivSearch.setOnClickListener {
            binding.ivSearch.hideKeyboard()
        }

        binding.etSearchQuestions.doOnTextChanged { text, start, before, count ->
            filterAdapter(text)
        }
    }

    private fun initAdapter() {
        binding.rvFreqAskedQues.adapter = adapter
        adapter.itemClick = {
            findNavController().navigate(
                FreqAskedQuesFragmentDirections.actionFreqAskedQuesFragmentToFaqDetailFragment(it.id)
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
        if (data.all { it is FreqAskedQuesResponse }) {
            adapter.setData(data as ArrayList<FreqAskedQuesResponse>)
            tempArrayList.clear()
            tempArrayList.addAll(data)
        }
    }

    private fun filterAdapter(text: CharSequence?) {
        searchArrayList.clear()
        var count = 0
        var inputText = ""
        inputText = if (text!!.endsWith(" "))
            text.substring(0, text.length - 1)
        else
            text.toString()

        val temp = inputText.toLowerCase().split(" ")
        for (data in tempArrayList) {
            count = 0 // если хотябы одно слово найдет то должен добавить в адаптер
            for (i in temp) {
                if (data.issue.toLowerCase().contains(i) && i != " ") {
                    count++
                }
            }
            if (count != 0) {
                searchArrayList.add(data)
            }
        }
        adapter.setData(searchArrayList)
    }

    override fun successRequest() {

    }

}


