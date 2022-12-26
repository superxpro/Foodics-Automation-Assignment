package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {
    static XSSFWorkbook workbook;
    static XSSFSheet sheet;

    public ExcelUtils() {
    }

    public ExcelUtils(String excelPath, String sheetName) {
        try {
            workbook = new XSSFWorkbook(excelPath);
            sheet = workbook.getSheet(sheetName);

        } catch (Exception exp) {
            System.out.println(exp.getMessage());
            System.out.println(exp.getCause());
            exp.printStackTrace();
        }

    }


    public static void getRowCount() {

        int rowCount = sheet.getPhysicalNumberOfRows();
        System.out.println("No Of Rows: " + rowCount);

    }

    public static void getCellData() {

        DataFormatter formatter = new DataFormatter();
        Object username = formatter.formatCellValue(sheet.getRow(1).getCell(1));
        Object password = formatter.formatCellValue(sheet.getRow(1).getCell(2));

        System.out.println(username);
        System.out.println(password);

    }

    public static String[] readExcelFile(String fileName) throws IOException {
        // Create an input stream to read the Excel file
        FileInputStream inputStream = new FileInputStream(new File(fileName));

        // Create a Workbook object to read the Excel file
        Workbook workbook = new XSSFWorkbook(inputStream);

        // Get the first sheet of the workbook
        Sheet sheet = workbook.getSheetAt(0);

        // Get the first row of the sheet
        Row row = sheet.getRow(1);

        // Get the first and second cells of the row
        Cell usernameCell = row.getCell(0);
        Cell passwordCell = row.getCell(1);

        // Return the values of the cells as a string array
        return new String[]{usernameCell.getStringCellValue(), passwordCell.getStringCellValue()};
    }


}
