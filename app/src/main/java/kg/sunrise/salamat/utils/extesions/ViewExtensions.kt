 package kg.sunrise.salamat.utils.extesions

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Build
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.core.content.res.ResourcesCompat
import androidx.viewpager2.widget.ViewPager2
import androidx.navigation.findNavController
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import kg.sunrise.salamat.R
import timber.log.Timber
import android.widget.ImageView


fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun Context.toast(message: String, duration: Int = Toast.LENGTH_LONG): Toast =
    Toast.makeText(this, message, duration).apply {
        show()
    }

infix fun ViewGroup.inflate(@LayoutRes id: Int): View {
    return LayoutInflater.from(context).inflate(id, this, false)
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

//проверка стринга на входные и выходные данные
fun String.checkTitle(checkValue: String): Boolean {
    return this == checkValue
}

fun View.getBack() {
    findNavController().popBackStack()
}


fun setStatusColor(activity : Activity){
    val window = activity.window
    window.statusBarColor = activity.resources.getColor(R.color.violet_876ead)
}

fun View.convertToImage(imageName: String) {
    try {
        var bitmap = getBitmapFromView(this)
        if (bitmap != null) {
            saveMediaToStorage(bitmap, "$imageName.jpg")
        }
    } catch (e: Throwable) {
       Timber.e(e)
    }
}

fun ImageView.createBarcode(text: String){
    val multiFormatWriter = MultiFormatWriter()
    val width = resources.getDimension(R.dimen.barcode_width).toInt()
    val height = resources.getDimension(R.dimen.barcode_height).toInt()
    try {
        val bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.CODE_128, width, height)
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)

        for (xPix in 0 until  width){
            for (yPix in 0 until height){
                val color = when {
                    bitMatrix.get(xPix, yPix) -> Color.BLACK
                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                        resources.getColor(R.color.light_green_E6F1F7, null)
                    }
                    else -> {
                        resources.getColor(R.color.light_green_E6F1F7)
                    }
                }
                bitmap.setPixel(xPix, yPix, color)
            }
        }
        this.setImageBitmap(bitmap)
    } catch (e: WriterException) {
    }
}

fun setImageAsDrawable(context: Context, image: Int): Drawable {
    return ResourcesCompat.getDrawable(context.resources, image, null)!!
}

fun initTextView(view: TextView, value: String) {
    view.text = value
}

@SuppressLint("SetJavaScriptEnabled")
fun defaultWebView(webView: WebView, data: String) {
    webView.settings.javaScriptEnabled = true
    webView.clearCache(true)
    webView.webChromeClient = WebChromeClient()
    webView.settings.domStorageEnabled = true
    webView.settings.builtInZoomControls = true
    webView.loadDataWithBaseURL(
        null,
        "<style>img{display: inline;height: auto;max-width: 100%;}</style>$data",
        "text/html",
        "UTF-8", null
    )
}

