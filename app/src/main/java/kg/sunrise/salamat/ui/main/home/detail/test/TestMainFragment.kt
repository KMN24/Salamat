package kg.sunrise.salamat.ui.main.home.detail.test


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.fragment.BaseFragmentWithVM
import kg.sunrise.salamat.data.remote.response.TestQuestionsItem
import kg.sunrise.salamat.databinding.TestMainBinding
import kg.sunrise.salamat.ui.main.MainActivity.ShareViewModel
import kg.sunrise.salamat.ui.main.home.detail.test.adapter.TestMainAdapter
import kg.sunrise.salamat.ui.main.home.detail.test.detail.TestDetailFragment
import kotlinx.android.synthetic.main.fragment_test_question.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber


class TestMainFragment : BaseFragmentWithVM<TestMainBinding, TestMainViewModel>() {
    override val viewModel by viewModel<TestMainViewModel>()
    override val progressBar: ConstraintLayout by lazy {
        binding.inclProgress.clProgress
    }
    val args by navArgs<TestMainFragmentArgs>()
    val adapter by lazy {
        TestMainAdapter(this@TestMainFragment)
    }
    val shareViewModel by sharedViewModel<ShareViewModel>()
    private var testDetailFragment = TestDetailFragment()
    private var count = 0

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?): TestMainBinding {
        return TestMainBinding.inflate(inflater, container, false)
    }

    override fun init() {
        initRequest()
        nextPage()
        setupUI()
        observeChildFragmentForResult()
    }

    private fun setupUI() {
        backgroundSet(R.drawable.preface_white_bg)
    }

    private fun initRequest() {
        viewModel.getListTestQuestion(args.slug ?: "")
    }

    override fun findTypeOfObject(data: Any?) {
        when (data) {
            is ArrayList<*> -> {
                fillDataToAdapter(data)
            }
        }
    }


    private fun fillDataToAdapter(data: ArrayList<*>) {
        if (data.any { it is TestQuestionsItem? })
            initAdapter(data as ArrayList<TestQuestionsItem>)
    }

    private fun initAdapter(data: ArrayList<TestQuestionsItem>) {
        binding.vpTest.adapter = adapter
        binding.vpTest.isUserInputEnabled = false
        adapter.setData(data, args.title ?: "")
    }

    private fun observeChildFragmentForResult() {
    }

    private fun nextPage() {
        shareViewModel.testResultCount = 0
        binding.btnConfirm.setOnClickListener {
            val currentPage = binding.vpTest.currentItem
            val nextPage = currentPage + 1
            if (TestMainAdapter.isClickedOnAnswer) {
                if (nextPage == binding.vpTest.adapter?.itemCount)
                    findNavController().navigate(
                        TestMainFragmentDirections.actionTestMainFragmentToTestResultFragment(
                            shareViewModel.testResultCount,
                            args.slug!!, args.title!!
                        )
                    )
                binding.vpTest.setCurrentItem(nextPage, true)
                TestMainAdapter.isClickedOnAnswer = false
            } else
                toast(getString(R.string.answer_choose))
        }
    }


    override fun successRequest() {

    }
}
