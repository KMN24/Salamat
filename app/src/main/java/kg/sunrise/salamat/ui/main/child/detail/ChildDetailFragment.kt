package kg.sunrise.salamat.ui.main.child.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.fragment.BaseFragmentWithVM
import kg.sunrise.salamat.data.remote.response.ChildDetailResponse
import kg.sunrise.salamat.databinding.ChildDetailFragmentBinding
import kg.sunrise.salamat.utils.extesions.*
import kg.sunrise.salamat.utils.extesions.datePattern.DateTimeParser
import kg.sunrise.salamat.utils.extesions.datePattern.DateTimePattern
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class ChildDetailFragment : BaseFragmentWithVM<ChildDetailFragmentBinding, ChildDetailViewModel>() {
    private val args by navArgs<ChildDetailFragmentArgs>()
    override val viewModel by viewModel<ChildDetailViewModel>()
    var childDetailInfo: ChildDetailResponse? = null
    override val progressBar by lazy {
        binding.inclProgress.clProgress
    }

    override fun findTypeOfObject(data: Any?) {
        when (data) {
            is ChildDetailResponse ->
                childDetailInfo = data
        }
        initView(childDetailInfo!!)
    }

    private fun initView(data: ChildDetailResponse) {
        Glide.with(this).load(data.avatar).into(binding.ivAvatar)
        binding.etName.text = data.name.substring(1, data.name.length - 1)
        initGender()
            binding.tvBirthdate.text = DateTimeParser.formatDateTime(
                data.formattedBirthDate,
                DateTimePattern.D_MM_yyyy ,
                DateTimePattern.dd_MM_yyy_with_dots
            )
        binding.tvPreviousDate.text = data.prevVaccinationDate
            ?: getString(R.string.dash)
        binding.tvNextDate.text = data.nextVaccinationDate
            ?: getString(R.string.dash)
    }

    private fun initGender() {
        if (childDetailInfo!!.gender.checkTitle(getString(R.string.male_gender)))
            initTextView(binding.tvGender, getString(R.string.male_sex))
        if (childDetailInfo!!.gender.checkTitle(getString(R.string.gender_female)))
            initTextView(binding.tvGender, getString(R.string.female_sex))

    }


    override fun successRequest() {
        toast(getString(R.string.successfully_deleted))
        findNavController().navigate(ChildDetailFragmentDirections.actionChildDetailFragmentToChildFragment())
    }

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ChildDetailFragmentBinding {
        return ChildDetailFragmentBinding.inflate(inflater, container, false)
    }

    override fun init() {
        setupUI()
        initRequest()
        navigate()
        initDeleteListener()
    }

    private fun initDeleteListener() {
        binding.btnDelete.setOnClickListener {
            viewModel.deleteChild(args.slug ?: "")
        }
    }

    private fun setupUI() {
        backgroundSet(R.drawable.preface_white_bg)
    }

    private fun initRequest() {
        viewModel.getChildDetailInfo(args.slug ?: "")
    }

    private fun navigate() {
        binding.idNavBack.setOnClickListener {
            it.getBack()
        }
        binding.btnNow.setOnClickListener {
            findNavController().navigate(ChildDetailFragmentDirections.actionChildDetailFragmentToChildSubFragment(childDetailInfo!! , args.slug ?: ""))
        }
        binding.btnArchive.setOnClickListener {
            childDetailInfo?.let {
                findNavController().navigate(ChildDetailFragmentDirections.actionChildDetailFragmentToChildArchiveFragment(
                    it.slug, it.name))
            }        }
    }

}