package kg.sunrise.salamat.ui.main.home.doctors_appointment.step2

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.fragment.BaseFragmentWithVM
import kg.sunrise.salamat.data.remote.response.DoctorsResponse
import kg.sunrise.salamat.databinding.FragmentDoctorsAppointmentStep2Binding
import kg.sunrise.salamat.ui.main.home.doctors_appointment.step2.adapter.DoctorsAdapter
import kg.sunrise.salamat.utils.extesions.hideKeyboard
import kg.sunrise.salamat.databinding.ToolbarWithShadowBinding
import org.koin.android.viewmodel.ext.android.viewModel
import kg.sunrise.salamat.utils.extesions.setStatusColor


class DoctorsAppointmentStep2Fragment :
    BaseFragmentWithVM<FragmentDoctorsAppointmentStep2Binding, DoctorsAppointmentStep2ViewModel>() {
    override val viewModel by viewModel<DoctorsAppointmentStep2ViewModel>()
    override val progressBar: ConstraintLayout by lazy { binding.inclProgress.clProgress }
    private lateinit var toolbar: ToolbarWithShadowBinding
    private var isDoctorListEnable = false
    val adapter = DoctorsAdapter()

    private var searchDoctorsArrayList: ArrayList<DoctorsResponse> = ArrayList()
    private var doctorsArrayList: ArrayList<DoctorsResponse> = ArrayList()

    override fun init() {
        initToolbar()
        setUpUI()
        initAdapter()
        initListeners()

    }

    private fun initToolbar() {
        toolbar = binding.inclToolbar
        toolbar.tvTitle.text = getString(R.string.scheduling_appointments)
    }

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDoctorsAppointmentStep2Binding {
        return FragmentDoctorsAppointmentStep2Binding.inflate(inflater, container, false)
    }

    private fun setUpUI() {
        Glide.with(this).load(R.drawable.ic_search).into(binding.ivSearch)
        binding.rbFirstAppointment.buttonTintList = getRadioButtonColors()
        binding.rbReturnAppointment.buttonTintList = getRadioButtonColors()
        setStatusColor(requireActivity())
        showDoctorsList()
    }

    private fun showDoctorsList() {
        if (isDoctorListEnable){
            doctorListEnable(true)
        }
    }

    private fun initAdapter() {
        binding.rvDoctors.adapter = adapter
        adapter.itemClick = {
            isDoctorListEnable = true
            findNavController().navigate(
                DoctorsAppointmentStep2FragmentDirections
                    .actionDoctorsAppointmentStep2FragmentToDoctorsAppointmentStep3Fragment(
                        it.ppi
                    )
            )
        }

    }

    private fun initRequest() {
        viewModel.getDoctors()
    }

    private fun initListeners() {
        binding.btnSend.setOnClickListener {
            initRequest()
        }

        toolbar.ivArrowBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.rgAppointment.setOnCheckedChangeListener { group, checkedId ->
            sendBtnEnable(true)
        }

        binding.ivSearch.setOnClickListener { binding.edTSearchDoctorSpec.hideKeyboard() }

        binding.edTSearchDoctorSpec.doOnTextChanged { text, start, before, count ->
            filterAdapter(text)
        }
    }

    private fun getRadioButtonColors(): ColorStateList {
        return ColorStateList(
            arrayOf(
                intArrayOf(android.R.attr.state_checked),
                intArrayOf(android.R.attr.state_enabled)
            ),
            intArrayOf(
                resources.getColor(R.color.pink_B366F2),  // checked
                resources.getColor(R.color.black) // unchecked
            )
        )
    }

    override fun findTypeOfObject(data: Any?) {
        when (data) {
            is ArrayList<*> -> {
                checkArray(data)
            }
        }
    }

    private fun checkArray(data: ArrayList<*>) {
        if (data.all { it is DoctorsResponse }) {
            if (data.isNotEmpty()) {
                adapter.setData(data as ArrayList<DoctorsResponse>)
                doctorsArrayList.clear()
                doctorsArrayList.addAll(data)
                doctorListEnable(true)
            } else {
                showAlertDBEmpty()
            }
        }
    }

    private fun filterAdapter(text: CharSequence?) {
        searchDoctorsArrayList.clear()
        var inputText = ""
        inputText = if (text!!.endsWith(" "))
            text.substring(0, text.length - 1)
        else
            text.toString()
        val inputTextList = inputText.split(" ")

        doctorsArrayList.forEach { doctor ->
            if (inputTextList.any { text ->
                    doctor.fullName.contains(text, true) || doctor.speciality.contains(text, true)
                }) {
                searchDoctorsArrayList.add(doctor)
            }
        }
        adapter.setData(searchDoctorsArrayList)
    }

    private fun sendBtnEnable(isEnable: Boolean) {
        binding.btnSend.isEnabled = isEnable
    }

    private fun doctorListEnable(isDoctorsListEnable: Boolean) {
        binding.clChooseAppointment.visibility = View.GONE
        binding.clSearch.visibility = View.VISIBLE
        binding.rvDoctors.visibility = View.VISIBLE
    }

    private fun showAlertDBEmpty() {
        binding.tvNeZapolnenaBaza.visibility = View.VISIBLE
        binding.clChooseAppointment.visibility = View.GONE
    }

    override fun successRequest() {
    }
}