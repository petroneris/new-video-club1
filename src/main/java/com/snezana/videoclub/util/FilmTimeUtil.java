package com.snezana.videoclub.util;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Utility methods for film timestamp manipulation.
 */
public class FilmTimeUtil {

	/**
     * Utility: Returns String representation of given time
     * @param time epoch time in millis.
     * @return
     */
	public static String convertTime(long time) {
		Date date = new Date(time);		
		Format format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		return format.format(date);
	}

	 /**
     * Utility: Calculates remaining rental time.
     * @param time1 epoch time in milliseconds when rental started.
     * @param time2 epoch time in milliseconds to which rental overflow is
     * observed, notably the present moment.
     * @return Human readable information about remaining rental time.
     */
	public static String remainingTime(long time1, long time2) {
		  // Max rental period - 10 hours.
        long rentTime = 10 * 60 * 60 * 1000;
		String message = "";
		long diff = time1 + rentTime - time2;		
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000) % 24;
		long diffDays = diff / (24 * 60 * 60 * 1000);
		message = " " + diffDays + "d/" + diffHours + "h/" + diffMinutes + "min";
		return message;
	}

	/**
     * Utility: returns current (epoch) time in millis.
     * @return
     */
	public static long currentTime() {
		Calendar cal = Calendar.getInstance();
		long currTime = cal.getTimeInMillis();
		return currTime;
	}

	/**
     * Utility: Checks if rental time has been exceeded.
     * @param time1 epoch time in milliseconds when rental started.
     * @param time2 epoch time in milliseconds to which rental overflow is
     * observed.
     * @return {@code true} if rental time has exceeded maximum allowed value.
     */
	public static boolean remainOrExceed(long time1, long time2) {
		long rentalPeriod = 10 * 60 * 60 * 1000; //rental period for film - 10h ( for testing )
		long diff = time1 + rentalPeriod - time2;
		if (diff > 0) {
			return true;
		}
		return false;
	}
}
