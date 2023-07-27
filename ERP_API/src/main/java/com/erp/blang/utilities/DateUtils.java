package com.erp.blang.utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;

import com.erp.blang.model.BaseModel;


public class DateUtils {

	private static final String DEFAULTTIMEZONE = "Europe/London";
	private static Logger logger = Logger.getLogger(DateUtils.class);

	public static XMLGregorianCalendar getXMLGregorianCalendar(String timeZone, int days, int minutes) throws DatatypeConfigurationException {
		LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of(timeZone));
		if(days >0 ) {
			localDateTime = localDateTime.minusDays(days);
		} else {
			localDateTime = localDateTime.minusMinutes(minutes);
		}
		GregorianCalendar e = GregorianCalendar.from(localDateTime.atZone(ZoneId.of(timeZone)));
		return DatatypeFactory.newInstance().newXMLGregorianCalendar(e);
	}

	public static XMLGregorianCalendar getCurrentCalendar(String timeZone) throws DatatypeConfigurationException {

		LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of(timeZone));
		GregorianCalendar e = GregorianCalendar.from(localDateTime.atZone(ZoneId.of(timeZone)));
		return DatatypeFactory.newInstance().newXMLGregorianCalendar(e);
	}

	public static XMLGregorianCalendar getCurrentCalendar() throws DatatypeConfigurationException {

		LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of(DEFAULTTIMEZONE));
		GregorianCalendar e = GregorianCalendar.from(localDateTime.atZone(ZoneId.of(DEFAULTTIMEZONE)));
		return DatatypeFactory.newInstance().newXMLGregorianCalendar(e);
	}

	public static XMLGregorianCalendar stringToXMLGregorianCalander(String stringDate, String pattern) throws ParseException, DatatypeConfigurationException{
		DateFormat format = new SimpleDateFormat(pattern);
		Date date = format.parse(stringDate);
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		return  DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
	}

	public static String utcFormatString(String format,Date date) {

		SimpleDateFormat sf = new SimpleDateFormat(format, Locale.US);
		sf.setTimeZone(TimeZone.getTimeZone("UTC"));
		return sf.format(date);

	}

	public static XMLGregorianCalendar stringToDate(String date) {
		XMLGregorianCalendar calendar = null;
		try {
			String format = "yyyy-MM-dd'T'HH:mm:ss.SSSX";
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(new SimpleDateFormat(format).parse(date));
			calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar( cal);
		} catch (ParseException | DatatypeConfigurationException e) {
			logger.info(new StringBuilder("Exception"), e);
		} 
		return calendar;
	}

	public static Date parseMsDate(String date) {
		try {
			return new Date(Long.valueOf(date.substring(6,date.length()-2)));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}


	public static Date getPreviousDate(int days) {
		try {
			Calendar cal = new GregorianCalendar();
			cal.setTime(new Date());
			cal.add(Calendar.DAY_OF_MONTH, -days);
			return cal.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Date getNextDate(int days, Date date) {
		try {
			Calendar cal = new GregorianCalendar();
			cal.setTime(new Date());
			cal.add(Calendar.DAY_OF_MONTH, days);
			return cal.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Date getNextDateGeo(int days,XMLGregorianCalendar date) {
		try {
			Calendar cal = date.toGregorianCalendar();
			cal.add(Calendar.DAY_OF_MONTH, days);
			return cal.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date stringToDate(String date,String pattern) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			return format.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String dateToString(Date date,String pattern) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			return format.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public static String dateToStringUTC(Date date,String pattern, String timeZone) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			format.setTimeZone(TimeZone.getTimeZone(timeZone));
			return format.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * Converts java.util.Date to javax.xml.datatype.XMLGregorianCalendar
	 */
	public static XMLGregorianCalendar toXMLGregorianCalendar(Date date){
		GregorianCalendar gCalendar = new GregorianCalendar();
		gCalendar.setTime(date);
		XMLGregorianCalendar xmlCalendar = null;
		try {
			xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);
		} catch (DatatypeConfigurationException ex) {
			ex.printStackTrace();

		}
		return xmlCalendar;
	}

	/*
	 * Converts XMLGregorianCalendar to java.util.Date in Java
	 */
	public static Date toDate(XMLGregorianCalendar calendar){
		if(calendar == null) {
			return null;
		}
		return calendar.toGregorianCalendar().getTime();
	}

	public static XMLGregorianCalendar getWorkingDaysAfter(int noOfDays, String timeZone) {
		LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of(timeZone));
		GregorianCalendar e = GregorianCalendar.from(localDateTime.atZone(ZoneId.of(timeZone)));
		e.add(Calendar.DAY_OF_MONTH, noOfDays);
		while(isWorkingDay(e)){
			e.add(Calendar.DAY_OF_MONTH, 1);
		}
		DatatypeFactory dataTypeFactory = null;
		XMLGregorianCalendar xmlGregorianCalendar = null;
		try {
			dataTypeFactory = DatatypeFactory.newInstance();
			xmlGregorianCalendar = dataTypeFactory.newXMLGregorianCalendar(e);
		} catch (DatatypeConfigurationException e1) {
			logger.info(new StringBuilder("Exception"), e1);
		}
		return xmlGregorianCalendar;
	}

	public static XMLGregorianCalendar getWorkingDaysBefore(int noOfDays, String timeZone) {
		LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of(timeZone));
		GregorianCalendar e = GregorianCalendar.from(localDateTime.atZone(ZoneId.of(timeZone)));
		e.add(Calendar.DAY_OF_MONTH, -noOfDays);
		while(isWorkingDay(e)){
			e.add(Calendar.DAY_OF_MONTH, 1);
		}
		DatatypeFactory dataTypeFactory = null;
		try {
			dataTypeFactory = DatatypeFactory.newInstance();
		} catch (DatatypeConfigurationException e1) {
			e1.printStackTrace();
		}
		return dataTypeFactory.newXMLGregorianCalendar(e);
	}

	private static boolean isWorkingDay(Calendar calendar) {
		// set calendar time with given date
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		// check if it is Saturday(day=7) or Sunday(day=1)
		if ((dayOfWeek == 7) || (dayOfWeek == 1)) {
			return true;
		}
		return false;
	}

	public static void setBaseData(BaseModel model,String userId) {
		try {
			if(null==model.getCreatedAt()) {
				model.setCreatedAt(getCurrentCalendar());
			}
			model.setModifiedAt(getCurrentCalendar());
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		if(null==model.getCreatedBy()) {
			model.setCreatedBy(userId);
		}
		model.setModifiedBy(userId);
	}
	
	public static void setBaseData(BaseModel model,String userId,String userName) {
		try {
			if(null==model.getCreatedAt()) {
				model.setCreatedAt(getCurrentCalendar());
			}
			model.setModifiedAt(getCurrentCalendar());
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		if(null==model.getCreatedBy()) {
			model.setCreatedBy(userId);
		}
		model.setModifiedBy(userId);

	}

	public static void setModifiedBaseData(BaseModel model,String userId,String userName) {
		try {
			model.setModifiedAt(getCurrentCalendar());
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		model.setModifiedBy(userId);
		
	}
	
	public static void setModifiedBaseData(BaseModel model,String userId) {
		try {
			model.setModifiedAt(getCurrentCalendar());
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		model.setModifiedBy(userId);	
	}

	public static String converNumberToTime(long number) {
		int hours = (int) number / 3600;
		int remainder = (int) number - hours * 3600;
		int mins = remainder / 60;
		remainder = remainder - mins * 60;
		int secs = remainder;
		return (hours>0?String.format("%02d", hours)+" Hours ":"")+(mins>0?String.format("%02d", mins)+" Minutes ":"")+String.format("%02d", secs)+" Seconds";
	}
	
	public static XMLGregorianCalendar getFutureDate(Date date,int days) {
		if(date == null) date = new Date();
		return toXMLGregorianCalendar(getNextDate(days, date));
	}
	
	
	public static XMLGregorianCalendar getFutureGeo(XMLGregorianCalendar date,int days) {
		return toXMLGregorianCalendar(getNextDateGeo(days, date));
	}
	
	public static boolean compare(XMLGregorianCalendar cal) {
		Date date = cal.toGregorianCalendar().getTime();
		Date startDate = getZeroTimeDate(date);
		Date endDate = getZeroTimeDate(new Date());		
		boolean flag = false;		
		if(startDate.equals(endDate)) {
			flag = true;
		}		
		return flag;
	}
	
	public static Date getZeroTimeDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		date = calendar.getTime();
		return date;
	}
	
	public static Date getHourTimeDate(Date date, int j) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, j);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		//calendar.set(Calendar.MILLISECOND, 0);
		date = calendar.getTime();
		return date;
	}	
	
	public static Date getFutureDateAsDate(Date date,int days) {
		if(date == null) date = new Date();
		date.setTime(date.getTime()+30*24*60*60*1000);
		return date;
	}
}
