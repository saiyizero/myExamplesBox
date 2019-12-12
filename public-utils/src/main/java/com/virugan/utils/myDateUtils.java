/*@(#)MyDateUtils.java   2015-10-12 
 * Copy Right 2015 Bank of Communications Co.Ltd.
 * All Copyright Reserved
 */

package com.virugan.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class myDateUtils {


    public static final String format(Date date, String pattern){
        return (new SimpleDateFormat(pattern)).format(date);
    }

    public static final Date toDate(String dateStr,String pattern){
        SimpleDateFormat sDateFormat=new SimpleDateFormat(pattern);
        Date date=null;
        try {
            date = sDateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static final Date parse(String date, String pattern){
        try{
            return (Date) (new SimpleDateFormat(pattern)).parse(date);
        }catch(ParseException e){
            throw new RuntimeException((new StringBuilder("string date value[")).append(date).append("] parsing using [").append(pattern).append("] parse error").toString());
        }
    }
    

    public static final String currentTime() {
    	SimpleDateFormat df=new SimpleDateFormat("HH:mm:ss");
    	return df.format(new Date());
    }
    

    public static final Date dateAdd(Date indate,int addDate) {
    	Calendar c = Calendar.getInstance();  
		c.setTime(indate);
		c.add(Calendar.DATE, addDate);  
		return c.getTime();
    }
    

    public static final boolean compare(Date min,Date max){
		if (min.getTime() > max.getTime()) {
			return false;
		}else{
			return true;
		}
    }
}
