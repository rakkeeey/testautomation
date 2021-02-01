package com.seleniumeasy.framework.config;

import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;

/**
 * @author Rakesh This component class consists of wrapper methods for handling
 *         test data and control the test case flow based on the available
 *
 *
 */

public class DataTable {

	public static WritableWorkbook workbookSetData, workbookSetValue;
	public static Workbook workbookGetRecord, workbookGetValue, workbookGetData, workbookgetDataValues;
	public static Log logger = new Log4JLogger("AjaxFormPageLogger");

	public void importSheet(String path, int iteration) {

	}

	/**
	 * This method will set the "data" to the columnname by the given keyValue
	 * 
	 * @param keyValue
	 * @param columnname
	 * @param sheetID
	 * @param dataToPut
	 * @param dataProvider
	 * @param identifier
	 * @throws BiffException
	 * @throws IOException
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	public static void setData(String keyValue, String columnname, String sheetID, String dataToPut,
			String dataProvider, String identifier)
			throws BiffException, IOException, RowsExceededException, WriteException {

		try {

			File f = new File(dataProvider);

			WritableSheet sheet;

			Workbook wb = Workbook.getWorkbook(f);
			workbookSetData = Workbook.createWorkbook(f, wb);
			logger.info(workbookSetData.getNumberOfSheets());

			int startRow, startCol;

			sheet = workbookSetData.getSheet(sheetID);

			Cell tableStart = sheet.findCell(identifier);
			startRow = tableStart.getRow();
			startCol = tableStart.getColumn();

			Cell tableEnd = sheet.findCell(identifier, startCol + 1, startRow + 1, 100, 64000, false);
			int endCol = tableEnd.getColumn();
			int endRow = tableEnd.getRow();

			Cell cellKey = sheet.findCell(keyValue, startCol, startRow, endCol, endRow, false);
			int reqdRow = cellKey.getRow();

			Cell cellColumnname = sheet.findCell(columnname, startCol, startRow, endCol, endRow, false);
			int reqdCol = cellColumnname.getColumn();

			Label label = new Label(reqdCol, reqdRow, dataToPut);
			sheet.addCell(label);

		} catch (Exception e) {
			logger.error("Data not set " + e.getMessage());
		} finally {
			workbookSetData.write();
			workbookSetData.close();
		}
	}

	/**
	 * This method will get the "multiple data" from all columns in a table by
	 * the given keyValue
	 * 
	 * @param keyValue
	 * @param dataProvider
	 * @param sheetID
	 * @param tablename
	 * @return
	 * @throws BiffException
	 * @throws IOException
	 * @throws RowsExceededException
	 */
	public static Map<String, String> getDataValues(String keyValue, String dataProvider, String sheetID,
			String tablename) throws BiffException, IOException, RowsExceededException {

		try {

			FileInputStream f = new FileInputStream(dataProvider);

			Sheet sheet;

			workbookgetDataValues = Workbook.getWorkbook(f);
			int startRow, startCol;

			sheet = workbookgetDataValues.getSheet(sheetID);

			logger.info("value of sheet name:::::" + sheet.getName());

			logger.info(tablename);

			Cell tableStart = sheet.findCell(tablename);
			startRow = tableStart.getRow();
			logger.info("value of Rows    " + startRow);
			startCol = tableStart.getColumn();
			logger.info("value of cols    " + startCol);

			Cell tableEnd = sheet.findCell(tablename, startCol + 1, startRow + 1, 2000, 64000, false);
			int endCol = tableEnd.getColumn();
			int endRow = tableEnd.getRow();

			Cell cellKey = sheet.findCell(keyValue, startCol, startRow, endCol, endRow, false);
			int reqdRow = cellKey.getRow();
			// HashMap<String, Integer> m;
			Map<String, String> recordSet;
			// m = new HashMap<String, Integer>();
			recordSet = new HashMap<String, String>();
			for (int i = startCol + 1; i <= endCol - 1; i++) {
				Cell cols = sheet.getCell(i, (startRow));
				String colparam = cols.getContents();
				// logger.info("colparam"+colparam);
				// m.put(colparam, new Integer(i));

				Cell cols1 = sheet.getCell(i, reqdRow);
				// logger.info("value of cols "+cols1.getContents());
				recordSet.put(colparam, cols1.getContents());
			}

			return recordSet;
		} catch (Exception e) {

			logger.error("Data not found " + e.getMessage());
			return null;
		}

		finally {
			workbookgetDataValues.close();

		}
	}

}
