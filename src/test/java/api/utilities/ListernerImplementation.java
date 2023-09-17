package api.utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

/**
 * Implement ITestListner
 */
public class ListernerImplementation extends ExtentReportManager implements ITestListener {

	/**
	 * Extent Report Setup - OnSTART
	 */
	public void onStart(ITestContext context) {
		extent = ExtentSetup.setUpExtentReport();
	}

	/**
	 * Create Test - OnTestSTART
	 */
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
	}

	/**
	 * Add pass log - OnTestSUCCESS
	 */
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "TEST PASSED");
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
	}

	/**
	 * Add Fail log - OnTestFAIL
	 */
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, "TEST FAILED");
		test.log(Status.FAIL, result.getThrowable().getMessage());

	}

	/**
	 * add skip log - OnTestSKIP
	 */
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "TEST SKIPPED");
		test.log(Status.SKIP, result.getThrowable().getMessage());

	}

	/**
	 * write report - OnFINISH
	 */
	public void onFinish(ITestContext testContext) {
		extent.flush();
	}

}
