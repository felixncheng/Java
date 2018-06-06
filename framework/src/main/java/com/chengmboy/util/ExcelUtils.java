package com.chengmboy.util;

import java.io.File;

import com.chengmboy.util.common.ArrayUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author cheng_mboy
 */
public class ExcelUtils {

    /**
     * 读取excel表格
     *
     * @param startRow    从哪行开始读，通常是表头
     * @param startColumn 从哪里列开始读
     * @param file        excel
     * @param sheetAt     sheet位置
     * @return 表格数据
     */
    public static String[][] readExcel(int startRow, int startColumn, File file, int sheetAt) throws Exception {
        Workbook wb = new XSSFWorkbook(file);
        Sheet sheet = wb.getSheetAt(sheetAt);
        int rowNum = sheet.getLastRowNum();
        Row title = sheet.getRow(startRow);
        String[][] data = new String[rowNum - startRow][title.getLastCellNum() - startColumn];
        for (int i = startRow; i < rowNum; i++) {
            Row row = sheet.getRow(i);
            for (int j = startColumn; j < row.getLastCellNum(); j++) {
                String value;
                Cell cell = row.getCell(j);
                CellType cellType = cell.getCellTypeEnum();
                switch (cellType) {
                    case NUMERIC:
                        value = cell.getNumericCellValue() + "";
                        break;
                    case STRING:
                        value = cell.getStringCellValue();
                        break;
                    default:
                        value = "";
                }
                data[i - startRow][j - startColumn] = value;
            }
        }
        return data;
    }

    public static void main(String[] args) throws Exception {
        String[][] excel = ExcelUtils.readExcel(2, 0, new File("H:/Packages/微网国际通道代码格式.xlsx"), 0);
        for (int i = 1; i < excel.length; i++) {
            String s = excel[i][1];
            String[] split = s.split(" ");
            StringBuilder name= new StringBuilder();
            for (String s1 : split) {
                name.append("_").append(s1);
            }
            System.out.printf("%s(\"%s\",%s)", name.substring(1,name.length()).toUpperCase(), excel[i][2], excel[i][3].substring(0,excel[i][3].length()-2));
            System.out.println(",");
        }
    }
}
