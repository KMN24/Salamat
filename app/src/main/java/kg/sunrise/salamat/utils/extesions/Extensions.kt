package kg.sunrise.salamat.utils.extesions

import android.Manifest
import android.content.ContentValues
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import androidx.core.content.ContextCompat
import kg.sunrise.salamat.App
import kg.sunrise.salamat.R
import android.annotation.SuppressLint
import kg.sunrise.salamat.modules.baseUrl
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

fun prepareHtmlString(content: String): String {
    var result = ""
    val srcText = "src=\""
    val youtubeLink = "https://www.youtube.com/"
    val htmlString = "<html><body>$content</body></html>"
    return if (content.contains(youtubeLink))
        htmlString.replace(srcText + baseUrl + srcText ,  "$srcText $youtubeLink")
    else {
        result = htmlString
            .replace(srcText, srcText + baseUrl)
            .replace("http", "https")
        result
    }
}

fun tryExtension(func: () -> Unit) {
    try {
        func()
    } catch (e: Exception) {
        Timber.d(e)
    }
}

fun getBitmapFromView(view: View): Bitmap? {
    var bitmap =
        Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
    var canvas = Canvas(bitmap)
    view.draw(canvas)
    return bitmap
}

fun checkPermission(): Boolean {
    val result =
        ContextCompat.checkSelfPermission(App.context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    return result == PackageManager.PERMISSION_GRANTED
}

fun saveMediaToStorage(bitmap: Bitmap, imageName: String) {
    var fos: OutputStream? = null
    var file: File? = null
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        App.context.contentResolver?.also { resolver ->
            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, imageName)
                put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
            }

            val imageUri: Uri? =
                resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            fos = imageUri?.let { resolver.openOutputStream(it) }
        }
    } else {
        val imagesDir =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        file = File(imagesDir, imageName)
        fos = FileOutputStream(file)
    }
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
    App.context.toast(App.context.getString(R.string.ticket_successfully__download))
    fos?.flush()
    fos?.close()
    showInGallery(file.toString())
}

fun deleteTicket(imageName: String) {
    val path = Environment.getExternalStoragePublicDirectory(
        Environment.DIRECTORY_PICTURES
    )
    val file = File(path, "$imageName.jpg")
    if (file.exists()) {
        file.delete()
        showInGallery(file.toString())
    }
}

private fun showInGallery(file: String) {
    MediaScannerConnection.scanFile(
        App.context, arrayOf(file), null
    ) { path, uri ->
    }
}

@SuppressLint("SimpleDateFormat")
fun <T> formatDate(dateInput: T, pattern: String): String {
    val format = SimpleDateFormat(pattern)
    return format.format(dateInput)
}




