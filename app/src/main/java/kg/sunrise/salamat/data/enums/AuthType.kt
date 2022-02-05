package kg.sunrise.salamat.data.enums

import androidx.annotation.Keep
import java.io.Serializable

@Keep
enum class AuthType(private val intType: Int) : Serializable {
    AUTH(1), REGISTRATION(2)
}