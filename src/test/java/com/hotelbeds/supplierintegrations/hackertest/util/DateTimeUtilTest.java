package com.hotelbeds.supplierintegrations.hackertest.util;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import org.junit.Test;

public class DateTimeUtilTest {

	@Test
	public void minutesBetweenSameTimeZones() throws Exception {
		String time1 = "Thu, 21 Dec 2000 16:01:07 +0200";
		String time2 = "Thu, 21 Dec 2000 16:06:47 +0200";
		assertEquals(5, DateTimeUtil.minutesBetweenTimestampsRFC2822Format(time1, time2));

	}

	@Test
	public void minutesBetweenSameTimeZones2() throws Exception {
		String time1 = "Thu, 21 Dec 2000 16:06:47 +0200";
		String time2 = "Thu, 21 Dec 2000 16:01:17 +0200";
		assertEquals(5, DateTimeUtil.minutesBetweenTimestampsRFC2822Format(time1, time2));

	}

	@Test
	public void minutesBetweendifferentTimeZones() throws Exception {
		String time1 = "Thu, 21 Dec 2000 15:01:07 +0100";
		String time2 = "Thu, 21 Dec 2000 16:06:47 +0200";
		assertEquals(5, DateTimeUtil.minutesBetweenTimestampsRFC2822Format(time1, time2));

	}

	@Test
	public void minutesBetweendifferentTimeZones2() throws Exception {
		String time1 = "Thu, 21 Dec 2000 16:01:07 +0200";
		String time2 = "Thu, 21 Dec 2000 16:01:07 +0100";
		assertEquals(60, DateTimeUtil.minutesBetweenTimestampsRFC2822Format(time1, time2));

	}

	@Test
	public void minutesBetweendifferentTimeZones3() throws Exception {
		String time1 = "Thu, 21 Dec 2000 16:01:07 +0100";
		String time2 = "Thu, 21 Dec 2000 16:01:07 +0200";
		assertEquals(60, DateTimeUtil.minutesBetweenTimestampsRFC2822Format(time1, time2));

	}

	@Test(expected = ParseException.class)
	public void wrong_format_exception() throws ParseException {
		String time1 = "21 Dec 2000 16:01:07 +0200";
		String time2 = "Thu, 21 Dec 2000 16:06:47 +0200";
		DateTimeUtil.minutesBetweenTimestampsRFC2822Format(time1, time2);

	}

	@Test(expected = IllegalArgumentException.class)
	public void empty_argument_exception() throws ParseException {
		String time1 = "";
		String time2 = "Thu, 21 Dec 2000 16:06:47 +0200";
		DateTimeUtil.minutesBetweenTimestampsRFC2822Format(time1, time2);
	}

}
