package com.seleniumeasy.framework.config;

import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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


import javax.swing.JOptionPane;

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
	 * working parameterID ,SheetID is the name of the Sheet,identifier is the
	 * identifier, currentIteration is the row no of which the data value is to be
	 * set, dataProvider is the exact path of the excel sheet
	 * 
	 * @param parameterID
	 *            : is the Column name of which data is to be set
	 * @param SheetID
	 *            :is the name of the Sheet
	 * @param identifier
	 * @param currentIteration
	 * @param dataProvider
	 * @param valToSet
	 * @throws BiffException
	 * @throws IOException
	 * @throws WriteException
	 */
	public static void setDataValue(String parameterID, String SheetID, String identifier, int currentIteration, String

	dataProvider, String valToSet) throws BiffException, IOException, WriteException {

		File f = new File(dataProvider);
		WritableWorkbook workbook;
		WritableSheet sheet;
		try {
			Workbook wb = Workbook.getWorkbook(f);
			workbook = Workbook.createWorkbook(f, wb);
			logger.info(workbook.getNumberOfSheets());
			sheet = workbook.getSheet(SheetID);

			int startRow, startCol, KeyRow, endcol, endRow;
			Cell tableStart = sheet.findCell(identifier);
			startRow = tableStart.getRow();
			startRow = startRow + 2;
			KeyRow = (startRow + currentIteration) - 1;

			startCol = tableStart.getColumn();
			Cell tableEnd = sheet.findCell(identifier, startCol + 1, startRow + 1, 100, 64000, false);
			endcol = tableEnd.getColumn();
			endRow = tableEnd.getRow();

			HashMap<String, Integer> m = new HashMap<String, Integer>();
			for (int i = startCol + 1; i <= endcol - 1; i++) {

				Cell cols = sheet.getCell(i, (startRow - 2));
				String colparam = cols.getContents();

				m.put(colparam, new Integer(i));

			}

			try {

				Object colval = m.get(parameterID);
				int oi = (Integer) colval;

				Cell cellobj = sheet.getCell(oi, KeyRow - 1);
				// cellval = cellobj.getContents();
				int row = cellobj.getRow();
				int col = cellobj.getColumn();

				Label label = new Label(col, row, valToSet);
				if (currentIteration < endRow) {
					sheet.addCell(label);
				}
				workbook.write();
				workbook.close();

			} catch (Exception e) {
				logger.info("Error in setting parameter value " + e.getMessage());
			}

		} catch (FileNotFoundException e) {
			// No existing file found
			workbook = Workbook.createWorkbook(f); // Create a new one
		} catch (Exception e) {
			logger.error("Error writing date to Excel file: " + dataProvider + e.getMessage());
		}

	} 

	public static void setValue(String parameterID, String SheetID, String identifier, int currentIteration,
			String dataProvider, String valToSet) throws BiffException, IOException, WriteException {

		File f = new File(dataProvider);

		WritableSheet sheet;
		try {

			Workbook wb = Workbook.getWorkbook(f);
			workbookSetValue = Workbook.createWorkbook(f, wb);
			logger.info(workbookSetValue.getNumberOfSheets());
			sheet = workbookSetValue.getSheet(SheetID);

			int startRow, startCol, KeyRow, endcol, endRow;
			Cell tableStart = sheet.findCell(identifier);
			startRow = tableStart.getRow();
			startRow = startRow + 2;
			KeyRow = (startRow + currentIteration) - 1;

			startCol = tableStart.getColumn();
			Cell tableEnd = sheet.findCell(identifier, startCol + 1, startRow + 1, 100, 64000, false);
			endcol = tableEnd.getColumn();
			endRow = tableEnd.getRow();

			HashMap<String, Integer> m = new HashMap<String, Integer>();
			for (int i = startCol + 1; i <= endcol - 1; i++) {

				Cell cols = sheet.getCell(i, (startRow - 2));
				String colparam = cols.getContents();

				m.put(colparam, new Integer(i));

			}

			try {

				Object colval = m.get(parameterID);
				int oi = (Integer) colval;

				Cell cellobj = sheet.getCell(oi, KeyRow - 1);
				// cellval = cellobj.getContents();
				int row = cellobj.getRow();
				int col = cellobj.getColumn();

				Label label = new Label(col, row, valToSet);
				if (currentIteration < endRow) {
					sheet.addCell(label);
				}

			} catch (Exception e) {

				logger.error("Error in setting parameter value " + e.getMessage());
			}

		} catch (FileNotFoundException e) {
			// No existing file found
			workbookSetValue = Workbook.createWorkbook(f); // Create a new one
		} catch (Exception e) {
			logger.error("Error writing date to Excel file: " + dataProvider + e.getMessage());
		} finally {
			workbookSetValue.write();
			workbookSetValue.close();
		}

	} // // Set Parameter Value

	/**
	 * This method will set the "data" to the columnname by the given keyValue
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
			logger.error("Data not set "+ e.getMessage());
		} finally {
			workbookSetData.write();
			workbookSetData.close();
		}
	} 

	/**
	 * This method will get the "data" from the columnname by the given keyValue
	 * @param keyValue
	 * @param columnname
	 * @param sheetID
	 * @param dataProvider
	 * @param identifier
	 * @return
	 * @throws BiffException
	 * @throws IOException
	 * @throws RowsExceededException
	 */
	public static String getData(String keyValue, String columnname, String sheetID, String dataProvider,
			String identifier) throws BiffException, IOException, RowsExceededException {

		try {

			File f = new File(dataProvider);

			Sheet sheet;

			workbookGetData = Workbook.getWorkbook(f);

			int startRow, startCol;

			sheet = workbookGetData.getSheet(sheetID);

			Cell tableStart = sheet.findCell(identifier);
			startRow = tableStart.getRow();
			startCol = tableStart.getColumn();

			Cell tableEnd = sheet.findCell(identifier, startCol + 1, startRow + 1, 100, 64000, true);
			int endCol = tableEnd.getColumn();
			int endRow = tableEnd.getRow();

			Cell cellKey = sheet.findCell(keyValue, startCol, startRow, endCol, endRow, false);
			int reqdRow = cellKey.getRow();

			Cell cellColumnname = sheet.findCell(columnname, startCol, startRow, endCol, endRow, false);
			int reqdCol = cellColumnname.getColumn();

			Cell cellobj = sheet.getCell(reqdCol, reqdRow + 1);
			String cellval = cellobj.getContents();

			return cellval;
		} catch (Exception e) {

			logger.error("Data not found or not present");
			return null;
		}

		finally {
			workbookGetData.close();
		}
	} // getDATA

	/** This method will get the "multiple data" from all
	 * columns in a table by the given keyValue
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
			// workbookgetDataValues.getSheetNames();
			// logger.info("value of workbook
			// "+workbookgetDataValues.getSheetNames());
			// Sheet[] sheets =workbookgetDataValues.getSheets();
			// sheet = sheets[0];
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

			logger.error("Data not found "+e.getMessage());
			return null;
		}

		finally {
			workbookgetDataValues.close();

		}
	} // end of getDataValues

	/**
	 * This gets data with the provided arguments
	 * 
	 * @param SheetID
	 *            :is the name of the Sheet
	 * @param tableName
	 * @param keyValue
	 * @param dataProvider
	 * @param columnname
	 * @throws BiffException
	 * @throws IOException
	 * @throws WriteException
	 */
	public static String getData(String sheetID, String dataProvider, String tablename, int keyValue, String columnname)
			throws BiffException, IOException, RowsExceededException {

		try {

			String inputFile = null;
			inputFile = dataProvider;

			File inputWorkbook = new File(inputFile);

			Sheet sheet;

			workbookGetData = Workbook.getWorkbook(inputWorkbook);

			int startRow, startCol;

			sheet = workbookGetData.getSheet(sheetID);

			String cellIDARR[][] = new String[sheet.getRows()][sheet.getColumns()];
			logger.info("sheet.getRows() = " + cellIDARR.length);
			logger.info("sheet.getRows() = " + sheet.getRows());

			logger.info("sheet.getColumns() = " + sheet.getColumns());

			JOptionPane.showMessageDialog(null, "Getrowcount:    " + sheet.getRows());
			Cell tableStart = sheet.findCell(tablename);
			startRow = tableStart.getRow();
			logger.info("value of rows    " + startRow);
			startCol = tableStart.getColumn();
			logger.info("value of cols    " + startCol);

			Cell tableEnd = sheet.findCell(tablename, startCol + 1, startRow + 1, 2000, 64000, false);
			int endCol = tableEnd.getColumn();
			int endRow = tableEnd.getRow();
			logger.info("key value   " + keyValue);
			String key = Integer.toString(keyValue);
			logger.info("key    " + key);
			Cell cellKey = sheet.findCell(key, startCol, startRow, endCol, endRow, false);
			int reqdRow = cellKey.getRow();

			Cell cellColumnname = sheet.findCell(columnname, startCol, startRow, endCol, endRow, false);
			int reqdCol = cellColumnname.getColumn();

			Cell cellobj = sheet.getCell(reqdCol, reqdRow);
			String cellval = cellobj.getContents();
			JOptionPane.showMessageDialog(null, "Cell Content:    " + cellval);
			return cellval;

		} catch (Exception e) {
			logger.error("Data not found "+e.getMessage());

		}

		finally {
			workbookGetData.close();
		}
		return null;
	}

}
