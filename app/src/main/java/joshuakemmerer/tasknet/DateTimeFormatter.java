package joshuakemmerer.tasknet;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by temp on 10/13/2015.
 */
public class DateTimeFormatter
{

    /// converts 24-hour time to "HR:MIN PERIOD" format
    public static String format24HrTime(int hour, int minute)
    {
        if(hour % 12 != 0)
            return (hour % 12) + String.format(":%02d ", minute) + ((hour >= 12) ? "PM" : "AM");
        return String.format("12:%02d ", minute) + ((hour == 12) ? "PM" : "AM");
    }
    public static final SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a", Locale.US);

    /// converts Date object to "[name of day], [day of month] [month] [year]" format
    public static String formatDate(Date date)
    {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("EEE, dd MMM yyy", Locale.US);
        return dateFormatter.format(date);
    }
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyy", Locale.US);

}
