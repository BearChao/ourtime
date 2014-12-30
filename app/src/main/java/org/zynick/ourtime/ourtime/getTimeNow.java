package org.zynick.ourtime.ourtime;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zynick on 2014/12/29.
 */
public class getTimeNow {
    public static String getTimenow(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }
}
