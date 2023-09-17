package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * EXCEL utility methods
 */
public class XlUtility {

	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook wb;
	public XSSFSheet sheet;
	public XSSFCell cell;
	public XSSFRow row;
	public CellStyle style;
	String path;

	/**
	 * Get excel path in constructor during class load
	 * 
	 * @param path
	 */
	public XlUtility(String path) {
		this.path = path;
	}

	/**
	 * Get single row from excel
	 * 
	 * @param sheetName
	 * @return
	 * @throws IOException
	 */
	public int getRowCount(String sheetName) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(sheetName);
		int rowcount = sheet.getLastRowNum();
		wb.close();
		fi.close();
		return rowcount;

	}

	/**
	 * get cell count from excel sheet
	 * 
	 * @param sheetName
	 * @param rownum
	 * @return
	 * @throws IOException
	 */
	public int getCellCOunt(String sheetName, int rownum) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(sheetName);
		row = sheet.getRow(rownum);
		int cellcount = row.getLastCellNum();
		wb.close();
		fi.close();
		return cellcount;

	}

	/**
	 * get cell data from excel sheet
	 * 
	 * @param sheetName
	 * @param rowNum
	 * @param cellNum
	 * @return
	 * @throws IOException
	 */
	public String getCellData(String sheetName, int rowNum, int cellNum) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		cell = row.getCell(cellNum);

		DataFormatter format = new DataFormatter();
		String data;

		try {
			data = format.formatCellValue(cell);
		} catch (Exception e) {
			data = "";
		}
		wb.close();
		fi.close();
		return data;

	}

	/**
	 * write data in cell
	 * 
	 * @param sheetName
	 * @param rownum
	 * @param column
	 * @param data
	 * @throws IOException
	 */
	public void setCellData(String sheetName, int rownum, int column, String data) throws IOException {
		File xfile = new File(path);
		if (!xfile.exists()) // if file is not exists then create new file
		{
			wb = new XSSFWorkbook();
			fo = new FileOutputStream(path);
			wb.write(fo);
		}

		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);

		if (wb.getSheetIndex(sheet) == -1) // if sheet is not exists then create new sheet
		{
			wb.createSheet(sheetName);
			sheet = wb.getSheet(sheetName);
		}

		if (sheet.getRow(rownum) == null) // - if row is not exists then create new row
		{
			sheet.createRow(rownum);
			row = sheet.getRow(rownum);
		}

		cell = row.createCell(column);
		cell.setCellValue(data);
		fo = new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();

	}

	/**
	 * fill color in excel cell - PASS color : GREEN
	 * 
	 * @param sheetName
	 * @param rowNam
	 * @param colNum
	 * @throws IOException
	 */
	public void fillGreenColor(String sheetName, int rowNam, int colNum) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(sheetName);
		row = sheet.getRow(rowNam);
		cell = row.getCell(colNum);

		style = wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(style);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}

	/**
	 * fill color in excel cell - FAIL color : RED
	 * 
	 * @param sheetName
	 * @param rowNam
	 * @param colNum
	 * @throws IOException
	 */
	public void fillRedColor(String sheetName, int rowNam, int colNum) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(sheetName);
		row = sheet.getRow(rowNam);
		cell = row.getCell(colNum);

		style = wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(style);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}

}
