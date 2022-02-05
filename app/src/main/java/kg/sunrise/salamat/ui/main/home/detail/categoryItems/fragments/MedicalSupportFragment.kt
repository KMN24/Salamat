package kg.sunrise.salamat.ui.main.home.detail.categoryItems.fragments

import android.app.DatePickerDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.recyclerview.widget.LinearLayoutManager
import kg.sunrise.salamat.base.fragment.BaseFragment
import kg.sunrise.salamat.data.remote.response.CheckListDTO
import kg.sunrise.salamat.databinding.MedicalSupportBinding
import kg.sunrise.salamat.ui.main.home.detail.categoryItems.fragments.adapter.MedicalAdapter
import java.util.*
import kotlin.collections.ArrayList


class MedicalSupportFragment() : BaseFragment<MedicalSupportBinding>() {
    val medicalAdapter by lazy {
        MedicalAdapter()
    }
    private var list: ArrayList<CheckListDTO> = arrayListOf()

    override fun init() {
        initAdapter()
    }

    private fun initAdapter() {
        binding.medicalRecycler.adapter = medicalAdapter
        binding.medicalRecycler.layoutManager = LinearLayoutManager(context)
        medicalAdapter.setData(list)
    }

    fun populatedata(data: ArrayList<CheckListDTO>) {
        list = data
    }


    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): MedicalSupportBinding {
        return MedicalSupportBinding.inflate(inflater, container, false)
    }


}