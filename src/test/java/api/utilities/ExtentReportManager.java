package api.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import api.test.PetTest;
import api.test.UserTest;

/**
 * Load class objects dynamically
 */
public class ExtentReportManager {
	public static ExtentReports extent;
	public static ExtentTest test;
	public static UserTest userTest;
	public static PetTest petTest;

}
