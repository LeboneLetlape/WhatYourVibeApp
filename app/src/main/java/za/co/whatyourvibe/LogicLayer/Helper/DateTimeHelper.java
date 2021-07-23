package za.co.whatyourvibe.LogicLayer.Helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateTimeHelper {

    public static Date GetDateFromTime(String _stringTime) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("hh:mm");
        return formatter.parse(_stringTime);
    }

    public static String GetFormatedTime(Date _dateTime)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm aa");
        return dateFormat.format(_dateTime).toUpperCase();
    }

    public static String GetFormatedDate(Date _dateTime)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        return dateFormat.format(_dateTime).toUpperCase();
    }

    public static Calendar GetDateFromCalendar(Date _dateTime)
    {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(String.valueOf(Locale.getDefault())));
        cal.setTime(_dateTime);
        return cal;
    }
}
