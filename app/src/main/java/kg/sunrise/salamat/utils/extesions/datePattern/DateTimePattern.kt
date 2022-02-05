package kg.sunrise.salamat.utils.extesions.datePattern

enum class DateTimePattern(val pattern: String) {
    /** yyyy-MM-dd'T'HH:mm:ss  */
    DATE_TIME_ISO("yyyy-MM-dd'T'HH:mm:ss"),

    /** yyyy-MM-dd'T'HH:mm */
    yyyy_MM_dd_HH_mm("yyyy-MM-dd'T'HH:mm"),

    /** yyyy-MM-dd  */
    yyyy_MM_dd_with_dash("yyyy-MM-dd"),

    /** dd.MM.yyyy  */
    dd_MM_yyy_with_dots("dd.MM.yyyy"),

    /** dd MMM yyyy  */
    dd_MM_yyyy_with_spacing("dd MMM yyyy"),

    /** D MM YYYY */
    D_MM_yyyy("d.MM.yyyy"),

    /** HH:mm:ss  */
    HH_mm_ss("HH:mm:ss"),

    /** HH:mm  */
    HH_mm("HH:mm"),

    /** MM.dd.yyyy HH:mm  */
    MM_dd_yyyy_hh_mm("MM.dd.yyyy HH:mm");


}

