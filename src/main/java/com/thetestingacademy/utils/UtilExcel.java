package com.thetestingacademy.utils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UtilExcel {

    // File Input - the File
    // Get the Data into a Object[][]


    // File Input - the File
    static Workbook book;
    static Sheet sheet;
    public static String SHEET_NAME = System.getProperty("user.dir")+"/src/main/java/com/thetestingacademy/resources/TDATB4X.xlsx";


    public static Object[][] getTestDataFromExcel(String sheetName) throws IOException {

        FileInputStream  file = null;
        try {
            file = new FileInputStream(SHEET_NAME);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        book = WorkbookFactory.create(file);
        sheet = book.getSheet(sheetName);
        Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
        for (int i = 0; i < sheet.getLastRowNum() ; i++) {
            for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
                data[i][j] = sheet.getRow(i+1).getCell(j).toString();
            }
        }

        return data;
    }



    @DataProvider
    public Object[][] getData() throws IOException {
        return getTestDataFromExcel("Sheet1");
    }


}
