package kg.sunrise.salamat.ui.main.home.doctors_appointment.step3


data class DoctorsAppointmentStep3DTO(
    var yearAppointment: Int? = null,
    var monthAppointment: Int? = null,
    var dayAppointment: Int? = null,
    var appointmentId: Int = 0,
    var appointmentDate: String = ""
)