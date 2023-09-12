package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "DataUsers")
	public String[][] getAllData() throws IOException {
		String path = System.getProperty("user.dir") + "//testData//userData.xlsx";
		XlUtility xl = new XlUtility(path);

		int rowNum = xl.getRowCount("Sheet1");
		int colCount = xl.getCellCOunt("Sheet1", rowNum);

		String apiData[][] = new String[rowNum][colCount];

		for (int i = 1; i <= rowNum; i++) {

			for (int j = 0; j < colCount; j++) {
				apiData[i - 1][j] = xl.getCellData("Sheet1", i, j);
			}
		}
		return apiData;

	}
	
	@DataProvider(name = "UserNames")
	public String[] getUserNames() throws IOException
	{
		String path = System.getProperty("user.dir") + "//testData////userData.xlsx";
		XlUtility xl = new XlUtility(path);

		int rowNum = xl.getRowCount("Sheet1");

		String apidata[] = new String[rowNum];

		for (int i = 1; i <= rowNum; i++) {
			apidata[i - 1] = xl.getCellData("Sheet1", i, 1);
		}

		return apidata;

	}
}
