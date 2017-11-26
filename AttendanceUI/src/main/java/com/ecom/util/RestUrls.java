package com.ecom.util;

public class RestUrls {
	//public static String ip = "103.206.248.235:9991";
	public static String ip = "127.0.0.1:9991";

	public static String urlPrefix = "http://" + ip;

	public static String registerURL = urlPrefix + "/IMSServices/Register/industry";
	public static String checkDateURL = urlPrefix + "/IMSServices/TimerService/getTimerDate";
	public static String advancePayURL = urlPrefix + "/IMSServices/PaymentService/giveAdvance/";

	public static String updateAttendanceURL = urlPrefix + "/IMSServices/AttendanceService/attendance/";
	public static String attendanceHistoryURL = urlPrefix + "/IMSServices/AttendanceService/attendanceSummary/";
	public static String refreshURL = urlPrefix + "/IMSServices/AttendanceService/refresh/";

	public static String calculateSalaryURL = urlPrefix + "/IMSServices/SalaryService/calculate/";
	public static String paySalaryURL = urlPrefix + "/IMSServices/SalaryService/paySalary/";
	public static String fullFinalURL = urlPrefix + "/IMSServices/SalaryService/fullAndFinal/";
	public static String calculateFullFinalURL = urlPrefix + "/IMSServices/SalaryService/fullAndFinalCalculate/";

	public static String getLabourListURL = urlPrefix + "/IMSServices/LabourService/list/";
	public static String updateLabourURL = urlPrefix + "/IMSServices/LabourService/updateLabour/";
	public static String addLabourURL = urlPrefix + "/IMSServices/LabourService/addLabour/";
}
