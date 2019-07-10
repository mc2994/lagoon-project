package com.lagoon.reports.util;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;

public class WorkbookConstants {

	// public static XSSFColor XSSF_COLOR_CONSTANTS_BLUE;

	public static HSSFFont createFont(HSSFWorkbook workbook, String fontName, boolean isFontBold, Short color,
			Short fontHeight) {
		HSSFFont font = workbook.createFont();
		font.setFontName(fontName);
		font.setBold(isFontBold);
		font.setColor(color);
		font.setFontHeightInPoints(fontHeight);

		return font;
	}

	public static HSSFCellStyle createBorderCellStyle(HSSFWorkbook workbook, HorizontalAlignment alignment,
			FillPatternType fillPattern, BorderStyle borderType) {
		return createBorderCellStyle(workbook, alignment, fillPattern, borderType, borderType, borderType, borderType);
	}

	public static HSSFCellStyle createBorderCellStyle(HSSFWorkbook workbook, HorizontalAlignment alignment,
			FillPatternType fillPattern, BorderStyle borderBottom, BorderStyle borderTop, BorderStyle borderRight,
			BorderStyle borderLeft) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(alignment);
		style.setFillPattern(fillPattern);
		style.setBorderBottom(borderBottom);

		// XSSFColor XSSF_COLOR_CONSTANTS_BLUE = new XSSFColor(new Color(149,
		// 179, 215));

		HSSFPalette palette = workbook.getCustomPalette();
		HSSFColor myColor = palette.findSimilarColor(149, 179, 215);
		short palIndex = myColor.getIndex();

		style.setFillForegroundColor(palIndex);

		// style.setFillForegroundColor(XSSF_COLOR_CONSTANTS_BLUE.getIndex());
		style.setBorderTop(borderTop);
		style.setBorderRight(borderRight);
		style.setBorderLeft(borderLeft);

		return style;
	}

	public static HSSFCellStyle createBorderCellStyleDefault(HSSFWorkbook workbook, HorizontalAlignment alignment,
			BorderStyle borderStyle) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(alignment);
		style.setBorderBottom(borderStyle);
		style.setBorderTop(borderStyle);
		style.setBorderRight(borderStyle);
		style.setBorderLeft(borderStyle);

		return style;
	}

	public static void createMetaHeaderCell(HSSFWorkbook workbook, HSSFSheet sheet, String cellValue, Integer rowIndex,
			Integer cellIndex) {
		HSSFCellStyle style = WorkbookConstants.createBorderCellStyle(workbook, HorizontalAlignment.CENTER,
				FillPatternType.SOLID_FOREGROUND, BorderStyle.NONE);
		style.setFont(createFont(workbook, "Candara", true, IndexedColors.BLACK.getIndex(), (short) 11));
		HSSFRow row = sheet.getRow(rowIndex) == null ? sheet.createRow(rowIndex) : sheet.getRow(rowIndex);
		row.createCell(cellIndex).setCellValue(cellValue);
		row.getCell(cellIndex).setCellStyle(style);
	}

	public static void createMetaHeaderCellValue(HSSFWorkbook workbook, HSSFSheet sheet, String cellValue,
			Integer rowIndex, Integer cellIndex) {
		HSSFCellStyle rowstyle = createBorderCellStyleDefault(workbook, HorizontalAlignment.CENTER, BorderStyle.NONE);
		rowstyle.setFont(createFont(workbook, "Candara", false, IndexedColors.BLACK.getIndex(), (short) 11));
		HSSFRow row = sheet.getRow(rowIndex) == null ? sheet.createRow(rowIndex) : sheet.getRow(rowIndex);
		row.createCell(cellIndex).setCellValue(cellValue);
		row.getCell(cellIndex).setCellStyle(rowstyle);
	}
}