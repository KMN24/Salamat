package kg.sunrise.salamat.ui.main.child.childSubCategory.fragments.MedicalObserve

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.fragment.BaseFragmentWithVM
import kg.sunrise.salamat.data.remote.response.AllCheckItemsWithNotifications
import kg.sunrise.salamat.databinding.MedicalObservingBinding
import kg.sunrise.salamat.utils.extesions.datePattern.DateTimeParser
import kg.sunrise.salamat.utils.extesions.datePattern.DateTimePattern
import org.koin.android.viewmodel.ext.android.viewModel


class MedicalObservingFragment :
    BaseFragmentWithVM<MedicalObservingBinding, MedicalObserveViewModel>() {
    val adapter by lazy {
        MedicalObserveAdapter(requireContext())
    }
    override val progressBar: ConstraintLayout by lazy {
        binding.inclProgress.clProgress
    }
    private var slug = ""
    override val viewModel by viewModel<MedicalObserveViewModel>()
    var data = arrayListOf<AllCheckItemsWithNotifications>()

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): MedicalObservingBinding {
        return MedicalObservingBinding.inflate(inflater)
    }

    override fun init() {
        initAdapter()
        initRequest()

    }

    private fun initRequest() {
        adapter.request = {
            val date =
                DateTimeParser.formatDateTime(
                    adapter.date,
                    DateTimePattern.dd_MM_yyy_with_dots,
                    DateTimePattern.yyyy_MM_dd_with_dash
                )
            viewModel.postCheckListResp(
                date, data[it].id, slug
            )
        }
    }

    private fun initAdapter() {
        binding.rvMedical.adapter = adapter
        adapter.setData(data)
    }


    fun populateData(subData: ArrayList<AllCheckItemsWithNotifications>, slug: String) {
        this.slug = slug
        data = subData
    }

    override fun findTypeOfObject(data: Any?) {

    }

    override fun successRequest() {
        toast(getString(R.string.successfuly_saved_see_in_archive))
    }


}