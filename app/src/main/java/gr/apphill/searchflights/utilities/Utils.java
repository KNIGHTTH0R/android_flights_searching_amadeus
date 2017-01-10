package gr.apphill.searchflights.utilities;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Dimitrios on 04-Jan-17.
 */

public class Utils {

    public static String calcualteTimeDifference(String originTime, String destinationTime) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date originTimeFormat = formatter.parse(originTime);
        Date destinationTimeFormat = formatter.parse(destinationTime);
        long diff = destinationTimeFormat.getTime() - originTimeFormat.getTime();
        long hours = TimeUnit.MILLISECONDS.toHours(diff);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(diff) - (hours*60);
        if (hours < 0) {
            hours += 23;
        }
        if (minutes < 0) {
            minutes += 60;
        }
        return hours + "h " + minutes + "m";
    }//calcualteTimeDifference


}//Utils
