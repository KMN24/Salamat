package kg.sunrise.salamat.ui.main.home.detail.test.detail.result


import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.fragment.BaseFragment
import kg.sunrise.salamat.databinding.TestResultFragmentBinding
import kg.sunrise.salamat.databinding.ToolbarWithShadowBinding
import kg.sunrise.salamat.ui.main.home.detail.test.adapter.TestMainAdapter
import kg.sunrise.salamat.utils.extesions.getBack
import kg.sunrise.salamat.utils.extesions.initTextView
import kg.sunrise.salamat.utils.extesions.setImageAsDrawable

const val NO_RESULT = 0
 val AVERAGE_ANSWER = 3..4
 val LESS_ANSWERS = 1..2

class TestResultFragment : BaseFragment<TestResultFragmentBinding>() {
    lateinit var toolbar: ToolbarWithShadowBinding
    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): TestResultFragmentBinding {
        return TestResultFragmentBinding.inflate(inflater, container, false)
    }

    private val args by navArgs<TestResultFragmentArgs>()

    override fun init() {
        setupUI()
        initStars()
        navigating()
        initToolbar()
        initTextView(binding.tvDesc, getString(R.string.answered_correct, args.correctAnswers))
    }

    private fun initToolbar() {
        toolbar = binding.inclToolbar
        initTextView(toolbar.tvTitle, args.title)
        toolbar.ivArrowBack.setOnClickListener {
            it.getBack()
        }
    }

    private fun navigating() {
        binding.btnMenu.setOnClickListener {
            findNavController().navigate(TestResultFragmentDirections.actionTestResultFragmentToHomeFragment2())
        }
        binding.btnTestAgain.setOnClickListener {
            findNavController().navigate(
                TestResultFragmentDirections.actionTestResultFragmentToTestMainFragment(
                    args.slug,
                    args.title
                )
            )
        }
    }

    private fun setupUI() {
        backgroundSet(R.drawable.preface_white_bg)
    }

    private fun initStars() {
        when (args.correctAnswers) {
            NO_RESULT -> {
                setImageViewDrawable(binding.ivFirstStar, R.drawable.ic_little_star_white)
                setImageViewDrawable(binding.ivMainStar, R.drawable.ic_little_star_white)
                setImageViewDrawable(binding.ivLastStar, R.drawable.ic_little_star_white)
            }
            in LESS_ANSWERS -> {
                setImageViewDrawable(binding.ivMainStar, R.drawable.ic_little_star_white)
                setImageViewDrawable(binding.ivLastStar, R.drawable.ic_little_star_white)
            }
            in AVERAGE_ANSWER -> {
                setImageViewDrawable(binding.ivLastStar, R.drawable.ic_little_star_white)
            }
        }

    }

    private fun setImageViewDrawable(imageView: ImageView, drawable: Int) {
        imageView.setImageDrawable(setImageAsDrawable(requireContext(), drawable))
    }


}