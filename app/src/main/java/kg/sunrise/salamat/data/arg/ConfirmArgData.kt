package kg.sunrise.salamat.data.arg

import androidx.annotation.Keep
import kg.sunrise.salamat.data.enums.AuthType
import java.io.Serializable

@Keep
data class ConfirmArgData(
    val phone : String,
    val type : AuthType
) : Serializable