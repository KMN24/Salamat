package kg.sunrise.salamat.ui.main.child.childAdd.alerts

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import android.view.View
import android.widget.DatePicker
import android.widget.TextView
import androidx.annotation.RequiresApi
import kg.sunrise.salamat.R
import kg.sunrise.salamat.utils.extesions.datePattern.DateTimeParser
import kg.sunrise.salamat.utils.extesions.datePattern.DateTimePattern
import kg.sunrise.salamat.utils.extesions.formatDate
import timber.log.Timber
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Locale.*
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.regex.Pattern

class ChooseDate(val context: Context) {
    lateinit var calendar: Calendar
    var day = 0
    var month = 0
    var year = 0
    lateinit var datePicker: DatePickerDialog
    var date = ""

    @SuppressLint("SimpleDateFormat", "NewApi")
    fun createDatePicker(textView: TextView) {
        calendar = Calendar.getInstance()
        year = calendar.get(Calendar.YEAR)
        month = calendar.get(Calendar.MONTH)
        day = calendar.get(Calendar.DAY_OF_MONTH)
        datePicker = DatePickerDialog(
            context,
            { view: DatePicker?, year: Int, month: Int, dayOfMonth: Int ->
                val currentDate = context.getString(R.string.date_string, year, month + 1, dayOfMonth)
                date = currentDate
                textView.text = DateTimeParser.formatDateTime(currentDate, DateTimePattern.yyyy_MM_dd_with_dash , DateTimePattern.dd_MM_yyy_with_dots)
                textView.setTextColor(context.resources.getColor(R.color.red_cf1066))
            }, year, month, day
        )
        datePicker.datePicker.maxDate = System.currentTimeMillis()
        datePicker.datePicker.touchables[0].performClick()
        datePicker.show()
    }
}
