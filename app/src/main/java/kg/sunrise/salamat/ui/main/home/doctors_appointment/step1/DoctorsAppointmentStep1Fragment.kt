package kg.sunrise.salamat.ui.main.home.doctors_appointment.step1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.fragment.BaseFragmentWithVM
import kg.sunrise.salamat.data.remote.response.PatientResponse
import kg.sunrise.salamat.databinding.FragmentDoctorsAppointmentStep1Binding
import kg.sunrise.salamat.databinding.ToolbarWithShadowBinding
import kg.sunrise.salamat.utils.extesions.hideKeyboard
import kg.sunrise.salamat.utils.extesions.setStatusColor
import kg.sunrise.salamat.utils.preference.setRegistraturaToken
import org.koin.android.viewmodel.ext.android.viewModel


class DoctorsAppointmentStep1Fragment :
    BaseFragmentWithVM<FragmentDoctorsAppointmentStep1Binding, DoctorsAppointmentStep1ViewModel>() {
    override val viewModel by viewModel<DoctorsAppointmentStep1ViewModel>()
    override val progressBar: ConstraintLayout by lazy { binding.inclProgress.clProgress }
    private lateinit var toolbar: ToolbarWithShadowBinding

    private var isBtnNextClicked = false
    private val PIN_LENGTH_SIZE = 14
    private val PHONE_LENGTH_SIZE = 13

    override fun init() {
        initToolbar()
        setUpUI()
        initListeners()
    }

    private fun initToolbar() {
        toolbar = binding.inclToolbar
    }

    private fun setUpUI() {
        setStatusColor(requireActivity())
        toolbar.tvTitle.text = getString(R.string.scheduling_appointments)
    }

    private fun initListeners() {
        binding.btnNext.setOnClickListener {
            getInfoPatient()
            binding.edTPhone.hideKeyboard()
            binding.edTPin.hideKeyboard()
        }

        binding.btnClear.setOnClickListener {
            binding.clPatientInfo.visibility = View.GONE
            binding.btnClear.visibility = View.GONE
            isPatientInfoEnabled(false)

        }

        toolbar.ivArrowBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.edTPin.doOnTextChanged { text, start, before, count ->
            isPatientInfoEnabled(false)
            isBtnNextEnable()
        }


        binding.edTPhone.doOnTextChanged { text, start, before, count ->
            isPatientInfoEnabled(false)
            isBtnNextEnable()
        }

    }

    private fun getInfoPatient() {
        val pin = binding.edTPin.text.toString()
        val phone = binding.edTPhone.text.toString()

        if (!isBtnNextClicked){
            if (pin.length == PIN_LENGTH_SIZE && phone.length == PHONE_LENGTH_SIZE){
                isPatientInfoEnabled(true)
                viewModel.getPatient(pin, binding.edTPhone.unMasked)
            }
        }else{
            findNavController().navigate(
                DoctorsAppointmentStep1FragmentDirections
                    .actionDoctorsAppointmentStep1FragmentToDoctorsAppointmentStep2Fragment()
            )
            isPatientInfoEnabled(false)
        }
    }

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDoctorsAppointmentStep1Binding {
        return FragmentDoctorsAppointmentStep1Binding.inflate(inflater, container, false)
    }

    override fun findTypeOfObject(data: Any?) {
        if( data is PatientResponse){
            showInfoAboutPatient(data)
        }
    }

    private fun showInfoAboutPatient(data: PatientResponse) {

        binding.clPatientInfo.visibility = View.VISIBLE
        binding.btnClear.visibility = View.VISIBLE

        setRegistraturaToken(requireContext(), data.access)
        binding.apply {
            data.apply {
                tvOrganization.text = patient.gsvName
                tvGsvAddress.text = patient.gsvAddress
                tvFmcName.text = patient.fmcName
                tvFmcAddress.text = patient.fmcAddress
                tvPhonesRegistry.text = patient.gsvPhone
                tvFio.text = patient.fullName
                tvStatusMhi.text = if(patient.statusMhi) getString(R.string.insured) else getString(
                                    R.string.uninsured)

            }
        }

    }

    private fun isPatientInfoEnabled(isEnable: Boolean){
        isBtnNextClicked = isEnable
    }

    override fun successRequest() {
    }

    private fun isBtnNextEnable(){
        binding.btnNext.isEnabled = binding.edTPin.text?.length == PIN_LENGTH_SIZE && binding.edTPhone.text?.length == PHONE_LENGTH_SIZE
    }

}