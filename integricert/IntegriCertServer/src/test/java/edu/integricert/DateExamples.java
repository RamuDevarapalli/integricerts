package edu.integricert;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;

public class DateExamples {
	public static void main(String[] args) throws Exception {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");
		Date date1 = simpleDateFormat.parse("18-02-2024 16:30");
		Date date2 = simpleDateFormat.parse("19-02-2024 11:30");
		Date date3 = simpleDateFormat.parse("20-02-2024 10:30");

		List<Long> dates = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			dates.add(DateUtils.addMinutes(date1, i * 30).getTime());
			dates.add(DateUtils.addMinutes(date2, i * 30).getTime());
			dates.add(DateUtils.addMinutes(date3, i * 30).getTime());
		}
		System.out.println(dates);
	}
}
