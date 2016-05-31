package mcl.jejunu.ac.homesharing.formatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by neo-202 on 2016-05-31.
 */
public class DayFormatter{

    private static DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public static String format(Date date){
        return formatter.format(date);
    }
}
