package com.hotelbeds.supplierintegrations.hackertest.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Write a function that returns the number of minutes (rounded down) between two
//timestamps time1 and time2 in RFC 2822 format (ie: Thu, 21 Dec 2000 16:01:07 +0200).
//Don’t forget about the time zones.

public class DateTimeUtil {
	private static final Logger logger = LoggerFactory.getLogger(DateTimeUtil.class);

	public static long minutesBetweenTimestampsRFC2822Format(String time1, String time2) throws ParseException {

		if (null == time1 || time1.isEmpty() || null == time2 || time2.isEmpty()) {
			throw new IllegalArgumentException("time1 and time2 must not be empty");
		}
		logger.info("minutesBetweenTimestampsRFC2822Format  time1 {} and time2 {}", time1, time2);

		String pattern = "EEE, dd MMM yyyy HH:mm:ss Z";
		SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.ENGLISH);
		Date time1Date = format.parse(time1);
		Date time2Date = format.parse(time2);
		long dif = Math.abs((time2Date.getTime() - time1Date.getTime()));
		long difMin = (long) Math.floor(dif / 60000);
		logger.info("dif "+dif);
		logger.info("dif in Min: "+difMin);
		return difMin;
	}
}
