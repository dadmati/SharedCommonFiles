package commonfunctions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelReader {

public static HSSFWorkbook InItWorkbook(String sFilePath)

{

FileInputStream fileInputStream = null;

HSSFWorkbook workbook = null;

try {

fileInputStream = new FileInputStream(sFilePath);

} catch (FileNotFoundException e1) {

e1.printStackTrace();

}

try {

workbook = new HSSFWorkbook(fileInputStream);

} catch (IOException e) {

e.printStackTrace();

}

return workbook;

}

//public static String ReadCellValue(HSSFSheet worksheet, int iRow, int iCell)

public static String ReadCellValue(HSSFSheet worksheet, int iRow, int iCell)

{

String val = null;

try

{

HSSFRow row = worksheet.getRow(iRow);

HSSFCell cell = row.getCell(iCell);

val = cell.getStringCellValue();


}

catch(Exception e)

{

val = null;

}


return val;

}


public static int GetRowCount(HSSFSheet worksheet, int iCellReff)

{

int iRowCount = 0;

int iRow=0;

String sCellValue = null;

do

{

sCellValue =  ReadCellValue(worksheet, iRow, iCellReff);

iRowCount++;

iRow++;

}

while(sCellValue != null);

iRowCount--;

return iRowCount;

}


public static void WriteCellValue(String sWBPath, String sWorksheet, int iRow, int iCell, String val)

{

FileInputStream fileInputStream = null;

HSSFWorkbook workbook = null;


try {

fileInputStream = new FileInputStream(sWBPath);

} catch (FileNotFoundException e1) {

e1.printStackTrace();

}

try {

workbook = new HSSFWorkbook(fileInputStream);

} catch (IOException e) {



e.printStackTrace();

}

HSSFSheet worksheet = workbook.getSheet(sWorksheet);

HSSFRow row = worksheet.getRow(iRow);

HSSFCell cell = row.getCell(iCell);

cell.setCellValue(val);


// Write the output to a file

    FileOutputStream fileOut = null;

try {

fileOut = new FileOutputStream(sWBPath);

} catch (FileNotFoundException e1) {

e1.printStackTrace();

}

    try {

workbook.write(fileOut);

} catch (IOException e) {

e.printStackTrace();

}

    try {

fileOut.close();

} catch (IOException e) {

e.printStackTrace();

}

}

}


