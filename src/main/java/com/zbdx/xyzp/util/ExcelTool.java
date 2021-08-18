package com.zbdx.xyzp.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * @Auther: mm
 * @Date: 2020/6/29 10:45
 * @Description:
 */
@Slf4j
public class ExcelTool {

    public static XSSFCellStyle getTitleStyle(XSSFWorkbook workbook) {
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle = setBorder(cellStyle);
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 12);
        font.setFontName("宋体");
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return cellStyle;
    }

    public static XSSFCellStyle getCommonStyle(XSSFWorkbook workbook) {
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle = setBorder(cellStyle);
        cellStyle.setFont(getFont(workbook));
        cellStyle.setAlignment(HorizontalAlignment.RIGHT);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return cellStyle;
    }

    public static XSSFCellStyle getCenterStyle(XSSFWorkbook workbook) {
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle = setBorder(cellStyle);
        cellStyle.setFont(getFont(workbook));
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return cellStyle;
    }

    public static XSSFCellStyle setBorder(XSSFCellStyle cellStyle) {
        //上边框
        cellStyle.setBorderTop(BorderStyle.THIN);
        //下边框
        cellStyle.setBorderBottom(BorderStyle.THIN);
        //左边框
        cellStyle.setBorderLeft(BorderStyle.THIN);
        //右边框
        cellStyle.setBorderRight(BorderStyle.THIN);
        return cellStyle;
    }

    public static XSSFFont getFont(XSSFWorkbook workbook) {
        XSSFFont font = workbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 12);
        return font;
    }

    public static XSSFSheet createTitleRow(XSSFSheet sheet, int index, String[] titles, CellStyle style) {
        XSSFRow titleRow = sheet.createRow(index);
        for (int i = 0; i < titles.length; i++) {
            XSSFCell cell = titleRow.createCell(i);
            cell.setCellStyle(style);
            cell.setCellValue(titles[i]);
            sheet.setColumnWidth(i, 22 * 256);
        }
        return sheet;
    }

    private static void createCenterCell(XSSFWorkbook workbook, XSSFRow row, String[] value) {
        for (int i = 0; i < value.length; i++) {
            createCenterCell(workbook, row, i, value[i]);
        }
    }

    private static void createCenterCell(XSSFWorkbook workbook, XSSFRow row, int col, String value) {
        XSSFCellStyle centerStyle = getCenterStyle(workbook);
        XSSFCell cell = row.createCell(col);
        cell.setCellValue(value);
        cell.setCellStyle(centerStyle);
    }


    private static void createSplitRow(XSSFWorkbook workbook, XSSFSheet sheet, int rowIndex, String lineName, boolean pointSplit) {
        XSSFRow row = sheet.createRow(rowIndex);
        if (pointSplit)
            row.setHeightInPoints(30);
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        setBorder(cellStyle);
        cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 12);
        cellStyle.setFont(font);
        for (int i = 0; i < 8; i++) {
            XSSFCell cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(lineName);
        }
        CellRangeAddress range3 = new CellRangeAddress(rowIndex, rowIndex, 0, 7);
        sheet.addMergedRegion(range3);
    }

    public static InputStream workbook2Stream(XSSFWorkbook workbook) {
        InputStream in = null;
        try {
            //临时缓冲区
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            //创建临时文件
            workbook.write(out);
            byte[] bookByteAry = out.toByteArray();
            in = new ByteArrayInputStream(bookByteAry);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return in;
    }

    /**
     * 根据单元格格式获取单元格内容
     * @param cell
     * @return
     */
//    public static String getCellValueForDate(XSSFCell cell){
//        String cellVal = "";
//        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
//            short format = cell.getCellStyle().getDataFormat();
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            if(format == 14 || format == 31 || format == 57 || format == 58){
//                //日期
//                sdf = new SimpleDateFormat("yyyy-MM-dd");
//            }else if (format == 20 || format == 32) {
//                //时间
//                sdf = new SimpleDateFormat("HH:mm");
//            }
//            double value = cell.getNumericCellValue();
//            Date date = DateUtil.getJavaDate(value);
//            cellVal = sdf.format(date);
//        }
//        return cellVal;
//    }

    /**
     * 根据单元格格式获取单元格内容
     * @param cell
     * @return
     */
//    public static String getCellValue(XSSFCell cell){
//        if(cell == null) {
//            return "";
//        }
//        if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
//            return cell.getStringCellValue();
//        }else if(cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN){
//            return String.valueOf(cell.getBooleanCellValue());
//        }else if(cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA){
//            return cell.getCellFormula() ;
//        }else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
//            String obj = String.valueOf(cell.getNumericCellValue());
//            obj = obj.substring(0,obj.indexOf("."));
//            return obj;
//        }
//        return cell.getStringCellValue();
//    }

    /**
     * 功能描述: 发送响应流方法
     */
    public static void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(),"ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                log.info("excel导出错误："+e);
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
            log.info("excel导出错误："+ex);
        }
    }
}
