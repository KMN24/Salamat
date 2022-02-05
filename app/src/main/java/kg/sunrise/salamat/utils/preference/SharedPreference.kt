package kg.sunrise.salamat.utils.preference

import android.content.Context
import androidx.core.content.edit
import java.util.*

private const val LANG_APP = "lang"
private const val ACCESS_UID = "uid"
private const val ACCESS_PHONE = "phone"
private const val ACCESS_TOKEN = "access-token"
private const val REGISTRATURA_TOKEN = "registratura-tokent"
const val EMPTY_VALUE = "PREF_EMPTY"

private const val SETTINGS_STORAGE_NAME = "ds.credo"

private fun pref(context: Context) =
    context.getSharedPreferences(SETTINGS_STORAGE_NAME, Context.MODE_PRIVATE)

private fun editString(context: Context, key: String, value: String?) = pref(context).edit {
    putString(key, value)
}

private fun getString(context: Context, key: String, default: String) =
    pref(context).getString(key, default) ?: default

fun setLocale(context: Context) {
    val locale = Locale(getLanguage(context))
    updateResourcesLocale(context, locale)
}

private fun updateResourcesLocale(context: Context, locale: Locale) {
    val configuration = context.resources.configuration
    configuration.setLocale(locale)
    context.createConfigurationContext(configuration)
}


fun setAccessUid(context: Context, uid: String?) = editString(context, ACCESS_UID, uid)

fun setAccessPhone(context: Context, phone: String?) = editString(context, ACCESS_PHONE, phone)

fun setAccessToken(context: Context, token: String?) = editString(context, ACCESS_TOKEN, token)

fun setLanguage(context: Context, lang: String) = editString(context, LANG_APP, lang)

fun getLanguage(context: Context) = getString(context, LANG_APP, "ru")

fun getPhone(context: Context) = getString(context, ACCESS_PHONE, EMPTY_VALUE)

fun getUid(context: Context) = getString(context, ACCESS_UID, EMPTY_VALUE)

fun getAccessToken(context: Context) = getString(context, ACCESS_TOKEN, EMPTY_VALUE)

fun setRegistraturaToken(context: Context, token: String) =
    editString(context, REGISTRATURA_TOKEN, token)

fun getRegistraturaToken(context: Context) = getString(context, REGISTRATURA_TOKEN, EMPTY_VALUE)

fun setEmptyToken(context : Context) = editString(context , ACCESS_TOKEN , EMPTY_VALUE)

