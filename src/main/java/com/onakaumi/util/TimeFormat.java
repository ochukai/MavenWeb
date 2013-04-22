package com.onakaumi.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public enum TimeFormat {
	Y_M_D("yyyy-MM-dd"),

	Y_M_D_h_M_S("yyyy-MM-dd hh:mm:ss"),

	Y_M_D_H_M_S("yyyy-MM-dd HH:mm:ss"),

	YMDHMSZ("yyyyMMddHHmmssZ"),

	h_M_S("hh:mm:ss"),

	H_M_S("HH:mm:ss"),

	h_M("hh:mm"),

	H_M("HH:mm");

	private final DateFormat format;

	private TimeFormat(final String pattern) {
		this.format = new SimpleDateFormat(pattern);
	}

	public String format(final Date date) {
		return format(format, date);
	}

	public String format(final Date date, final TimeZone zone) {
		return format(format, date, zone);
	}

	public String formatCST(final Date date) {
		return format(format, date, TimeZone.getTimeZone("CST"));
	}

	public String formatGMT(final Date date) {
		return format(format, date, TimeZone.getTimeZone("GMT"));
	}

	public String formatUTC(final Date date) {
		return format(format, date, TimeZone.getTimeZone("UTC"));
	}

	public DateFormat cloneFormat() {
		return (DateFormat) format.clone();
	}

	public Date parse(final String source) throws ParseException {
		return parse(format, source);
	}

	public static String format(final DateFormat format, final Date date) {
		return format.format(date);
	}

	public static String format(final DateFormat format, final Date date,
			final TimeZone zone) {
		DateFormat newFormat = (DateFormat) format.clone();
		newFormat.setTimeZone(zone);
		return newFormat.format(date);
	}

	public static String format(final String pattern, final Date date) {
		return format(new SimpleDateFormat(pattern), date);
	}

	public static String formatCST(final DateFormat format, final Date date) {
		return format(format, date, TimeZone.getTimeZone("CST"));
	}

	public static String formatGMT(final DateFormat format, final Date date) {
		return format(format, date, TimeZone.getTimeZone("GMT"));
	}

	public static String formatUTC(final DateFormat format, final Date date) {
		return format(format, date, TimeZone.getTimeZone("UTC"));
	}

	public static Date parse(final DateFormat format, final String source)
			throws ParseException {
		return format.parse(source);
	}

	public static Date parse(final String pattern, final String source)
			throws ParseException {
		return parse(new SimpleDateFormat(pattern), source);
	}
}
