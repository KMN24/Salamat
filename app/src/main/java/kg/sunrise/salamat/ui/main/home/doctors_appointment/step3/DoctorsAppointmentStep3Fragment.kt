package kg.sunrise.salamat.ui.main.home.doctors_appointment.step3


import android.Manifest
import android.app.DatePickerDialog
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Build
import android.os.Environment
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.fragment.BaseFragmentWithVM
import kg.sunrise.salamat.data.remote.response.*
import kg.sunrise.salamat.databinding.FragmentDoctorsAppointmentStep3Binding
import kg.sunrise.salamat.databinding.ToolbarWithShadowBinding
import kg.sunrise.salamat.ui.main.home.doctors_appointment.step3.adapter.DoctorDayScheduleAdapter
import kg.sunrise.salamat.utils.Constants
import kg.sunrise.salamat.utils.Constants.DATE_PICKER_LAN
import kg.sunrise.salamat.utils.extesions.*
import kg.sunrise.salamat.utils.preference.setLocale
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.util.*
import java.util.Locale


class DoctorsAppointmentStep3Fragment :
    BaseFragmentWithVM<FragmentDoctorsAppointmentStep3Binding, DoctorsAppointmentStep3ViewModel>() {
    override val viewModel by viewModel<DoctorsAppointmentStep3ViewModel>()
    override val progressBar by lazy { binding.inclProgress.clProgress }
    private lateinit var toolbar: ToolbarWithShadowBinding
    private val args by navArgs<DoctorsAppointmentStep3FragmentArgs>()
    private val adapter = DoctorDayScheduleAdapter()
    private val appointment = DoctorsAppointmentStep3DTO()

    override fun init() {
        initToolbar()
        setUpUI()
        initAdapter()
        initListeners()
    }

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDoctorsAppointmentStep3Binding {
        return FragmentDoctorsAppointmentStep3Binding.inflate(inflater, container, false)
    }

    private fun initToolbar() {
        toolbar = binding.inclToolbar
    }

    private fun setUpUI() {
        toolbar.tvTitle.text = getString(R.string.scheduling_appointments)
        setStatusColor(requireActivity())
    }

    private fun initAdapter() {
        binding.rvSchedule.adapter = adapter

        adapter.itemClick = {
            appointment.appointmentDate = it.datetime
            if (adapter.currentItem != adapter.prevItem) {
                adapter.currentItem?.setBackgroundResource(R.drawable.shape_all_12dp_red_e02523)
                if (adapter.hasPrevItemAppoint) {
                    adapter.prevItem?.setBackgroundResource(R.drawable.shape_all_12dp_red_e02523)
                } else {
                    adapter.prevItem?.setBackgroundResource(R.drawable.shape_all_12dp_blue_6294f3)
                }
                if (it.appointment != null) {
                    appointment.appointmentId = it.appointment.id
                    showAppointment(it.appointment)
                    adapter.hasPrevItemAppoint = true
                } else {
                    isScheduleEnable(true)
                    adapter.hasPrevItemAppoint = false
                }
            }
        }
    }

    private fun initListeners() {

        toolbar.ivArrowBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.clDatePicker.setOnClickListener {
            initDatePicker()
        }

        binding.btnSaveTicket.setOnClickListener {
            downloadTicket()
        }

        binding.btnSchedule.setOnClickListener {
            setAppointment(args.ppi, appointment.appointmentDate)
        }

        binding.btnCancel.setOnClickListener {
            isScheduleEnable(true)
            deleteAppointment(appointment.appointmentId)
        }

        binding.btnToHome.setOnClickListener {
            findNavController().navigate(
                DoctorsAppointmentStep3FragmentDirections.actionDoctorsAppointmentStep3FragmentToHomeFragment()
            )
        }
    }

    private fun getDoctorSchedule() {
        adapter.hasPrevItemAppoint = false
        adapter.prevItem = null
        // current month = monthAppointment!!+1, cause month starts by index 0
        with(appointment){
            viewModel.getDoctorSchedule(getString(R.string.date_string, yearAppointment, monthAppointment!! + 1, dayAppointment), args.ppi)
        }
    }

    private fun setAppointment(ppi: Int, date: String) {
        val appointment = AppointmentData(ppi, date)
        val postAppointment = AppointmentPost(appointment)
        viewModel.setAppointment(postAppointment)
    }

    private fun getAppointment(appointmentId: Int) {
        viewModel.getAppointment(appointmentId)
    }

    private fun downloadTicket() {
        val state = Environment.getExternalStorageState()
        if (Environment.MEDIA_MOUNTED == state) {
            if (Build.VERSION.SDK_INT >= Constants.ANDROID_VERSION_M) {
                if (checkPermission()) {
                    binding.clTicket.convertToImage(appointment.appointmentDate)
                } else {
                    requestPermission()
                }
            }
        }
    }

    private fun deleteAppointment(id: Int) {
        viewModel.deleteAppointment(id)
    }

    private fun initDatePicker() {
        Timber.e(Locale.getDefault().toString())
        val prevLocale = Locale.getDefault()

        val locale = Locale(DATE_PICKER_LAN)
        Locale.setDefault(locale)
        val configuration = resources.configuration
        configuration.setLocale(locale)
        context?.createConfigurationContext(configuration)

        val calendar = Calendar.getInstance()
        with(appointment){
            yearAppointment = calendar.get(Calendar.YEAR)
            monthAppointment = calendar.get(Calendar.MONTH)
            dayAppointment = calendar.get(Calendar.DAY_OF_MONTH)
        }

        val datePicker = DatePickerDialog(
            requireContext(), R.style.DoctorAppointmentDatePicker,
            { view: DatePicker?, year: Int, month: Int, dayOfMonth: Int ->
                binding.tvChooseDate.text = getString(R.string.date_appointment, getMonthText(month), dayOfMonth, year)
                with(appointment){
                    yearAppointment = year
                    monthAppointment = month
                    dayAppointment = dayOfMonth
                }
                setLocale(requireContext())
                Timber.e(Locale.getDefault().toString())
                getDoctorSchedule()
                isScheduleEnable(true)
                binding.btnSchedule.gone()
            }, appointment.yearAppointment!!, appointment.monthAppointment!!, appointment.dayAppointment!!
        )
        datePicker.setCancelable(false)
        datePicker.datePicker.minDate = System.currentTimeMillis()
        datePicker.window!!.setBackgroundDrawableResource(R.drawable.shape_all_12dp)
        datePicker.show()
        datePicker.getButton(DatePickerDialog.BUTTON_POSITIVE).setText(R.string.ok)
        datePicker.getButton(DatePickerDialog.BUTTON_NEGATIVE).setText(R.string.abolition)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            datePicker.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(resources.getColor(R.color.blue_6294F3, null))
            datePicker.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(resources.getColor(R.color.blue_6294F3, null))
        } else {
            datePicker.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(resources.getColor(R.color.blue_6294F3))
            datePicker.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(resources.getColor(R.color.blue_6294F3))
        }
    }

    private fun isScheduleEnable(isEnable: Boolean) {
        if (isEnable) {
            binding.btnCancel.gone()
            binding.btnSchedule.visible()
            binding.btnSaveTicket.gone()
            binding.clTicket.gone()

        } else {
            binding.btnCancel.visible()
            binding.btnSchedule.gone()
            binding.btnSaveTicket.visible()
            binding.clTicket.visible()
        }
    }

    override fun findTypeOfObject(data: Any?) {
        when (data) {
            is ArrayList<*> -> { checkArray(data) }
            is DoctorAppointmentResponse -> {
                binding.clTicket.visible()
                appointment.appointmentId = data.id
                showTicket(data)
                getDoctorSchedule()
            }

            is AppointmentResponse -> { }
        }
    }


    private fun checkArray(data: ArrayList<*>) {
        when {
            data.isEmpty() -> {
                toast(getString(R.string.choose_another_date))
                binding.rvSchedule.gone()
                isScheduleEnable(true)
                binding.btnSchedule.gone()
            }
            data.all { it is DoctorDayResponse } -> {
                adapter.setData(data as ArrayList<DoctorDayResponse>)
                binding.rvSchedule.visible()
            }
        }
    }

    override fun successRequest() {
        getDoctorSchedule()
        deleteTicket(appointment.appointmentDate)
        toast(getString(R.string.appointment_deleted))
        binding.btnSchedule.gone()
    }

    private fun showTicket(data: DoctorAppointmentResponse) {
        toast(getString(R.string.successfully_had_an_appointment))
        binding.tvGsvName.text = data.gsvName
        binding.tvAddressGsv.text = data.gsvAddress
        binding.tvDoctorName.text = data.doctorName
        binding.tvSpeciality.text = data.doctorSpeciality
        binding.tvFloorCabinet.text = getString(R.string.two_data_with_slash, data.floor.toString(), data.cabinet)
        binding.tvTextDate.text = getString(R.string.two_data_with_slash, data.appointmentDate, data.appointmentTime)
        binding.tvPatientName.text = data.patientName
        binding.tvStatusMhi.text = if (data.statusMhi) getString(R.string.insured) else getString(R.string.uninsured)
        setBarcode(data.patientPin)
        binding.tvTextCode128.text = data.patientPin
        binding.dateOfFormationOfTheTicket.text = data.createdAt

        isScheduleEnable(false)
    }

    private fun showAppointment(data: Appointment) {
        binding.tvGsvName.text = data.gsvName
        binding.tvAddressGsv.text = data.gsvAddress
        binding.tvDoctorName.text = data.doctorName
        binding.tvSpeciality.text = data.doctorSpeciality
        binding.tvFloorCabinet.text = getString(R.string.two_data_with_slash, data.floor.toString(), data.cabinet)
        binding.tvTextDate.text = getString(R.string.two_data_with_slash, data.appointmentDate, data.appointmentTime)
        binding.tvPatientName.text = data.patientName
        binding.tvStatusMhi.text = if (data.statusMhi) getString(R.string.insured) else getString(R.string.uninsured)
        setBarcode(data.patientPin)
        binding.tvTextCode128.text = data.patientPin
        binding.dateOfFormationOfTheTicket.text = data.createdAt
        isScheduleEnable(false)
    }

    private fun setBarcode(patientPin: String) {
        binding.ivCode128.createBarcode(patientPin)
    }

    private fun requestPermission() {
        if (context?.let { ActivityCompat.checkSelfPermission(it, Manifest.permission.WRITE_EXTERNAL_STORAGE) } != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), Constants.PERMISSION_WRITE_REQUEST_CODE)
        }
    }

    private fun getMonthText(month: Int): String = resources.getTextArray(R.array.months)[month].toString()

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            Constants.PERMISSION_WRITE_REQUEST_CODE ->
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    binding.clTicket.convertToImage(appointment.appointmentDate)
                } else {
                    toast(getString(R.string.require_write_permission))
                }
        }
    }

    override fun handleCustomError(message: String) {
        when {
            message.contains(getString(R.string.patient_already_has_had_a_appointment), true) -> toast(getString(R.string.already_have_an_appointment))
            message.contains(getString(R.string.time_has_passed), true) -> toast(getString(R.string.time_has_passed))
            message.contains(getString(R.string.appointment_wasnt_removed), true) -> toast(getString(R.string.time_has_passed))
            else -> toast(message)
        }
    }

}
