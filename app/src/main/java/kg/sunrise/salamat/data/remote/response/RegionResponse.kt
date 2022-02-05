package kg.sunrise.salamat.data.remote.response

data class RegionResponse (
    val username : String,
    val token : String,
    val region : String,
)

data class ChangeRegionResponse(
    val success: Boolean
)