package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * Extent Report initialization
 */
public class ExtentSetup extends ExtentReportManager {

	/**
	 * Intialize Extent report instance
	 * 
	 * @return
	 */
	public static ExtentReports setUpExtentReport() {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.ss").format(new Date());
		String repName = "Test-Report-" + timeStamp + ".html";
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(".\\extentReports\\" + repName);
		sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject");
		sparkReporter.config().setReportName("API Test Report");
		sparkReporter.config().setTheme(Theme.DARK);
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "API Test Report");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		return extent;
	}

}
