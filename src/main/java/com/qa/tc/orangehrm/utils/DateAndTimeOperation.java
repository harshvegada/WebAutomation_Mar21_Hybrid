package com.qa.tc.orangehrm.utils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateAndTimeOperation {

	private String[] getDateComponents() {
		Date date1 = Calendar.getInstance().getTime();  
		String sysDate = String.valueOf(date1);
		System.out.println("date: "+sysDate);
		String dateArray[] = sysDate.split(" ");
		return dateArray;
	}
	public static void main(String[] args) {
		DateAndTimeOperation dateAndTimeOperation = new DateAndTimeOperation();
		dateAndTimeOperation.systemCurrentTime();
	}
	
	public String systemDay() {
		String dateArray[] = getDateComponents();
		return dateArray[0];
	}
	
	public int systemDate() {
		String dateArray[] = getDateComponents();
		System.out.println(Integer.parseInt(dateArray[2]));
		return Integer.parseInt(dateArray[2]);
	}
	
	public String systemMonth() {
		String dateArray[] = getDateComponents();
		return dateArray[1];
	}

	public int systemYear() {
		String dateArray[] = getDateComponents();
		return Integer.parseInt(dateArray[5]);
	}
	
    public String systemCurrentTime() {
    	LocalTime time = LocalTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		return time.format(formatter);
    }
}
