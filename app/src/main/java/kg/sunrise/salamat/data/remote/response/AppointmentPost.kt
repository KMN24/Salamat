package kg.sunrise.salamat.data.remote.response

data class AppointmentPost( val appointment : AppointmentData)

data class AppointmentData(
    val ppi : Int,
    val appointment_date : String
)