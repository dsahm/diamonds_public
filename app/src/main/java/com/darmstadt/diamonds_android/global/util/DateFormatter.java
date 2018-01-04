package com.darmstadt.diamonds_android.global.util;

import com.orhanobut.logger.Logger;
import io.reactivex.annotations.Nullable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatter {

    private static final String LOG_TAG = DateFormatter.class.getSimpleName();

    public static final SimpleDateFormat FROM_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ", Locale.GERMAN);
    public static final SimpleDateFormat TO_FORMAT = new SimpleDateFormat("dd.MM.yyyy',' HH:mm 'Uhr'", Locale.GERMAN);

    public static String formatDate(String dateString) {
        try {
            final Date date = FROM_FORMAT.parse(dateString);
            dateString = TO_FORMAT.format(date);
        } catch (ParseException e) {
            Logger.log(Logger.ERROR, LOG_TAG, "Could not parse date: " + dateString, e);
            dateString = "";
        }
        return dateString;
    }

    public static @Nullable Date getDateFromString(final String dateString, final SimpleDateFormat dateFormat) {
        Date date = null;
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            Logger.log(Logger.ERROR, LOG_TAG, "Could not parse date: " + dateString, e);
        }
        return date != null ? date : new Date();
    }

}

