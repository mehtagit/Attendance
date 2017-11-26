package com.ecom.utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atttendance.pojos.Attendance;
import com.atttendance.pojos.Industry;
import com.atttendance.pojos.Labour;
import com.ecom.services.AttendanceService;
import com.ecom.services.IndustryService;
import com.ecom.services.LabourService;
import com.ecom.services.SalaryService;

@Service
public class BackEndService implements Runnable {

	final SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd-");

	final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	LabourService labourService;

	@Autowired
	AttendanceService attendanceService;

	@Autowired
	SalaryService salaryService;

	@Autowired
	IndustryService industryService;

	@PostConstruct
	public void startMe() {
		try {
			new Thread(this).start();
			System.out.println("BackEndService service has been started");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		boolean isYestRefreshedDone = false;
		while (true) {
			try {
				Thread.sleep(1000 * 60 * 1);
				int hh = getTimeInHours();
				// System.out.println("BackEndService service checking for hour
				// " + hh);

				if (hh == 0 && !isYestRefreshedDone) {
					List<Industry> industryList = industryService.getListOfIndustries();
					for (Industry industry : industryList) {
						markDefaultAttendance(industry);
					}
					isYestRefreshedDone = true;
					System.out.println("isYestRefreshedDone " + isYestRefreshedDone);
				} else if (hh >= 1 && isYestRefreshedDone) {
					isYestRefreshedDone = false;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void markDefaultAttendance(Industry industry) {
		try {
			boolean isHoliday = salaryService.isIndustryHasHoliday(industry.getIndustry_id(), getDate(-1));
			List<Labour> labourList = labourService.getLabourListWithoutPhoto(industry.getIndustry_id());
			for (Labour lb : labourList) {
				salaryService.fillAdminDetails(lb);
			}
			List<Attendance> yesterdayAttendanceList = attendanceService
					.getYesterdayAttendanceList(industry.getIndustry_id());

			for (Labour labour : labourList) {
				boolean isMarkedAttendance = false;
				for (Attendance attendance : yesterdayAttendanceList) {
					if (labour.getLabour_id().equals(attendance.getLabour_id())) {
						isMarkedAttendance = true;
						if (attendance.getStatus() == 1 && attendance.getOut_time() == null) {
							attendance.setOut_time(getDateDefaultTime(-1) + " " + industry.getDefaultOutTime());
							attendanceService.markAttendance(attendance);
						}
						break;
					}
				}

				try {
					if (!isMarkedAttendance) {
						System.out.println("Default Attendance Marked for " + labour);
						String attendanceId = getAttendanceID(-1) + labour.getLabour_id();
						Attendance attendance = new Attendance(attendanceId);
						attendance.setIndustry_id(industry.getIndustry_id());
						attendance.setLabour_id(labour.getLabour_id());
						attendance.setIn_time(null);
						attendance.setOut_time(null);
						attendance.setDatetime(getDate(-1) + " 00:00:00");
						/* if labour has its week off */
						int weekday = getDayOfWeek(attendance.getDatetime());
						String weekOffs[] = labour.getAdminDetail().getWeekoff_days().split("~");
						if (isHoliday) {
							attendance.setStatus(11);
						}
						else if (weekday == Integer.parseInt(weekOffs[0])) {
							attendance.setStatus(10);
						} else {
							/* Absent status */
							attendance.setStatus(0);
						}

						attendanceService.markAttendance(attendance);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getAttendanceID(int days) {
		final Date date = new Date();
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, days);
		return df.format(calendar.getTime());
	}

	public String getDate(int days) {

		final Date date = new Date();
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, days);
		return dateFormat.format(calendar.getTime());
	}

	public String getDateDefaultTime(int days) {

		final Date date = new Date();
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, days);
		return dateFormat.format(calendar.getTime());
	}

	private int getTimeInHours() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH");
		Calendar c1 = Calendar.getInstance();
		String CurrentDateTime = sdf.format(c1.getTime());
		return Integer.parseInt(CurrentDateTime);
	}

	public static int getDayOfWeek(String dateTime) {
		int day = 0;
		try {
			Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(dateTime.split(" ")[0]);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date1);
			day = cal.get(Calendar.DAY_OF_WEEK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return day;
	}
}
