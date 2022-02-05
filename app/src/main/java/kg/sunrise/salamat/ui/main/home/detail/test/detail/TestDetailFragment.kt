package kg.sunrise.salamat.ui.main.home.detail.test.detail


import android.graphics.drawable.Drawable
import android.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.fragment.BaseFragmentWithVM
import kg.sunrise.salamat.data.remote.response.Answer
import kg.sunrise.salamat.data.remote.response.TestQuestionsItem
import kg.sunrise.salamat.databinding.FragmentTestQuestionBinding
import kg.sunrise.salamat.databinding.ToolbarWithShadowBinding
import kg.sunrise.salamat.ui.main.MainActivity.ShareViewModel
import kg.sunrise.salamat.ui.main.home.detail.test.adapter.TestMainAdapter
import kg.sunrise.salamat.ui.main.home.detail.test.detail.adapter.TestQuestionAdapter
import kg.sunrise.salamat.utils.extesions.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class TestDetailFragment :
    BaseFragmentWithVM<FragmentTestQuestionBinding, TestDetailViewModel>() {
    var answer: Answer? = null
    val adapter = TestQuestionAdapter {
        answer = it
        clickListener()
    }
    private var count = 0
    private val testMainAdapter by lazy {
        TestMainAdapter(this@TestDetailFragment)
    }
    private var title = ""

    private lateinit var toolbar: ToolbarWithShadowBinding
    var arrayOftest: TestQuestionsItem? = null
    override val viewModel by viewModel<TestDetailViewModel>()
    val shareViewModel by sharedViewModel<ShareViewModel>()
    override val progressBar by lazy {
        binding.inclProgress.clProgress
    }


    override fun findTypeOfObject(data: Any?) {

    }

    override fun successRequest() {

    }

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTestQuestionBinding {
        return FragmentTestQuestionBinding.inflate(inflater, container, false)
    }

    override fun init() {
        setupUI()
        initText()
        initRecyclerView()
        setupToolbar()
    }

    private fun setupToolbar() {
        toolbar.ivArrowBack.setOnClickListener {
            it.getBack()
        }
        toolbar.tvTitle.text = title
    }

    private fun initRecyclerView() {
        binding.rvQuestions.adapter = adapter
        binding.rvQuestions.layoutManager = LinearLayoutManager(requireContext())
        arrayOftest?.let { adapter.setData(it.answers) }
    }

    fun populateData(data: TestQuestionsItem, title: String) {
        this.title = title
        arrayOftest = data
    }

    private fun initText() {
        initTextView(binding.tvQuestion, arrayOftest!!.questionRu)
        initTextView(binding.tvQuestionCount, getString(R.string.question, arrayOftest!!.id))
        adapter.isCLickedOnItem = true
    }

    private fun clickListener() {
        val view = adapter.view
        if (answer?.isCorrect == true) {
            shareViewModel.testResultCount += 1
            setupViewFor(view!!, setImageAsDrawable(requireContext(), R.drawable.btn_test_correct))
        } else {
            setupViewFor(view!!, setImageAsDrawable(requireContext(), R.drawable.btn_test_error))
        }
        if (view.isPressed)
            TestMainAdapter.isClickedOnAnswer = true
    }

    private fun setupViewFor(view: View, drawable: Drawable) {
        view.background = drawable
        adapter.isCLickedOnItem = false
    }

    private fun setupUI() {
        toolbar = binding.tbTestDetail
        backgroundSet(R.drawable.preface_white_bg)
        setStatusColor(requireActivity())
    }
}
