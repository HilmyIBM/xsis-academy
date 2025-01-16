package day04;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimes {

	public static void main(String[] args) {
		LocalDate dateObj = LocalDate.now();
		System.out.println(dateObj);
		
		LocalTime timeobj = LocalTime.now();
		System.out.println(timeobj);
		
		LocalDateTime datetimeObj = LocalDateTime.now();
		System.out.println(datetimeObj);
		
		DateTimeFormatter formatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		
		String formattedDate = datetimeObj.format(formatObj);
		System.out.println(formattedDate);
		
		LocalDate localdate = LocalDate.now();
		
		System.out.println(localdate);
		System.out.println(localdate.plusDays(3));
		
		DayOfWeek dayname = localdate.parse("2021-11-29").getDayOfWeek();
		System.out.println(dayname);
		
		LocalDate startdate = LocalDate.parse("2020-10-25");
		LocalDate enddate = LocalDate.parse("2021-11-29");
		
		long datecount = ChronoUnit.DAYS.between(startdate, enddate);
		System.out.println(datecount);
		long monthcount = ChronoUnit.MONTHS.between(startdate, enddate);
		System.out.println(monthcount);
		long yearcount = ChronoUnit.YEARS.between(startdate, enddate);
		System.out.println(yearcount);
		
		LocalDateTime starttime = LocalDateTime.parse("2022-01-15T10:00:00");
		Duration duration = Duration.between(starttime, starttime.plusMinutes(30));
		System.out.println(starttime + " - " + duration.toSeconds() + " - " + starttime.plusMinutes(30));

		LocalDateTime endtime = LocalDateTime.parse("2022-01-15T10:00:00");
		long seconds = ChronoUnit.SECONDS.between(starttime, endtime);
		long minutes = ChronoUnit.MINUTES.between(starttime, endtime);
		long hours = ChronoUnit.HOURS.between(starttime, endtime);
		long days = ChronoUnit.DAYS.between(starttime, endtime);
		long months = ChronoUnit.MONTHS.between(starttime, endtime);
		long years = ChronoUnit.YEARS.between(starttime, endtime);
		
		System.out.println(years + " tahun " + months + " Bulan " + days + " Hari,");
		System.out.println(hours + " Jam " + minutes + " Menit " + seconds + " Detik");
	}

}
