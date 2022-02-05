package kg.sunrise.salamat.ui.main.child.childAdd.parser

import android.net.Uri
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import timber.log.Timber
import java.io.File

class ContentParser() {
    fun putAvatar(imageUri : Uri) : MultipartBody.Part {
        val file = File(imageUri.path ?: "")
        val requestFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
            if (!file.exists()) {
                Timber.d("нет файла")
            }
       return MultipartBody.Part.createFormData("avatar" , file.name , requestFile)
        }
    fun getGender(value : String) : RequestBody {
        return RequestBody.create("text/plain".toMediaTypeOrNull(), value)
    }
    fun getDate(value: String) : RequestBody {
        return RequestBody.create("text/plain".toMediaTypeOrNull() , value)
    }
}