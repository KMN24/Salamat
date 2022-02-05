package kg.sunrise.salamat.ui.main.child.childSubCategory.fragments.ChildParameter


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.fragment.BaseFragmentWithVM
import kg.sunrise.salamat.data.remote.response.ChildParametersCharacter
import kg.sunrise.salamat.data.remote.response.ChildParametersInfoPost
import kg.sunrise.salamat.databinding.ChildParametersFragmentBinding
import kg.sunrise.salamat.ui.main.child.childSubCategory.fragments.ChildParameter.adapter.ChildParametersAdapter
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber


class ParametersChildFragment :
    BaseFragmentWithVM<ChildParametersFragmentBinding, ChildParameterViewModel>() {
    override val viewModel by viewModel<ChildParameterViewModel>()
    override val progressBar: ConstraintLayout by lazy {
        binding.inclProgress.clProgress
    }
    private var slug = ""
    private val adapter by lazy {
        ChildParametersAdapter()
    }
    private lateinit var titles: ArrayList<ChildParametersCharacter>

    override fun findTypeOfObject(data: Any?) {

    }

    override fun successRequest() {
        toast(getString(R.string.successfuly_saved_see_in_archive))
    }

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ChildParametersFragmentBinding {
        return ChildParametersFragmentBinding.inflate(inflater)
    }

    override fun init() {
        initArray()
        initRequest()
        initAdapter()
    }

    private fun initArray() {
        titles = arrayListOf(
            ChildParametersCharacter(getString(R.string.hemoglobin_child_parameter)),
            ChildParametersCharacter(getString(R.string.height_child_parameter)),
            ChildParametersCharacter(getString(R.string.weight_child_parameter))
        )
    }

    private fun initAdapter() {
        binding.rvChildParameters.adapter = adapter
        adapter.setData(titles)

    }

    fun populateSlug(slug: String) {
        this.slug = slug
    }

    private fun initRequest() {
        adapter.itemClick = {position : Int , tvCount : EditText ->
            viewModel.postChildDetailInfo(
                slug,
                ChildParametersInfoPost(
                    keyRu = titles[position].title,
                    value = tvCount.text.toString()
                )
            )
        }
    }
}

