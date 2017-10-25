package com.sdxd.framework.spreadsheet;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.springframework.util.Assert;

import static org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder.BorderSide.*;

/**
 * *****************************************************************************
 * <p>
 * 功能名           ：com.sdxd.common.web.util.spreadsheet
 * 系统名           ：
 * <p>
 * *****************************************************************************
 * Modification History
 * <p>
 * Date        Name                    Reason for Change
 * ----------  ----------------------  -----------------------------------------
 * 17/1/11     melvin                 Created
 */
public class SheetBuilder {

    private Spreadsheet spreadsheet;

    private Sheet sheet;

    private int currentRowIndex;

    private int maxColumnCount;
    private CellStyle errorStyle;

    public SheetBuilder(Spreadsheet spreadsheet, Sheet sheet) {
        Assert.notNull(spreadsheet, "Spreadsheet can not be null");
        Assert.notNull(sheet, "Sheet can not be null");
        this.spreadsheet = spreadsheet;
        this.sheet = sheet;
        this.currentRowIndex = 0;
        this.maxColumnCount = 0;

        Font errorFont = spreadsheet.newFont(font -> {
            font.setFontName("微软雅黑");
            font.setFontHeightInPoints((short) 10);
            font.setBoldweight((short) 5);
        });

        this.errorStyle = spreadsheet.newStyle(cellStyle -> {
            java.awt.Color red = java.awt.Color.RED;
            XSSFColor borderColor = spreadsheet.newColor(red.getRed(), red.getGreen(), red.getBlue());
            ((XSSFCellStyle) cellStyle).setFillForegroundColor(borderColor);
            cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
            cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
            cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            cellStyle.setBorderBottom(CellStyle.BORDER_DOUBLE);
            cellStyle.setBorderLeft(CellStyle.BORDER_DOUBLE);
            cellStyle.setBorderRight(CellStyle.BORDER_DOUBLE);
            cellStyle.setBorderTop(CellStyle.BORDER_DOUBLE);
            ((XSSFCellStyle) cellStyle).setBorderColor(TOP, borderColor);
            ((XSSFCellStyle) cellStyle).setBorderColor(BOTTOM, borderColor);
            ((XSSFCellStyle) cellStyle).setBorderColor(LEFT, borderColor);
            ((XSSFCellStyle) cellStyle).setBorderColor(RIGHT, borderColor);
            cellStyle.setFont(errorFont);
            cellStyle.setWrapText(true);
        });
    }

    public RowBuilder newRow(CellStyle defaultStyle) {
        Row row = sheet.createRow(this.currentRowIndex);
        this.currentRowIndex ++;
        return new RowBuilder(spreadsheet, this, row, defaultStyle);
    }

    public void addError(String error) {
        Row row = sheet.createRow(this.currentRowIndex);
        this.currentRowIndex ++;
        RowBuilder builder = new RowBuilder(spreadsheet, this, row, errorStyle);
        builder.cell(error);
        for (int i = 1; i <= maxColumnCount - 1; i ++) {
            builder.blank();
        }
//        Observable.range(1, maxColumnCount - 1).forEach(i -> builder.blank());
        sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), 0, this.maxColumnCount - 2));
    }

    int addCell(int index) {
        return addCell(index, 1);
    }

    int addCell(int index, int delta) {
        int newIndex = index + delta;
        if (newIndex > maxColumnCount) {
            maxColumnCount = newIndex;
        }
        return newIndex;
    }
}

