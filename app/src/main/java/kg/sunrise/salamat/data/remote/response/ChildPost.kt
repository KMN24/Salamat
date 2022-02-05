package kg.sunrise.salamat.data.remote.response

import okhttp3.MultipartBody
import okhttp3.RequestBody

data class ChildPost(val name: String,
                     val gender: RequestBody,
                     val avatar: MultipartBody.Part,
                     val birth_date: RequestBody,
                     val parent: Int)

