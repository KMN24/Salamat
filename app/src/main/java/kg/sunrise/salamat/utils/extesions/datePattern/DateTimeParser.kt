package kg.sunrise.salamat.utils.extesions.datePattern

import java.text.ParseException
import java.text.SimpleDateFormat

object DateTimeParser {
    @Throws(ParseException::class)
    fun parseFromISO(sourceISO: String?, destinationPattern: DateTimePattern): String {
        return formatDateTime(sourceISO, DateTimePattern.DATE_TIME_ISO, destinationPattern)
    }

    @Throws(ParseException::class)
    fun parseToISO(sourceDateTime: String?, sourcePattern: DateTimePattern): String {
        return formatDateTime(sourceDateTime, sourcePattern, DateTimePattern.DATE_TIME_ISO)
    }

    @Throws(ParseException::class)
    fun formatDateTime(
        sourceDateTime: String?,
        sourcePattern: DateTimePattern,
        destinationPattern: DateTimePattern
    ): String {
        val sourceFormat = SimpleDateFormat(sourcePattern.pattern)
        val destinationFormat = SimpleDateFormat(destinationPattern.pattern)
        return destinationFormat.format(sourceFormat.parse(sourceDateTime))
    }
}