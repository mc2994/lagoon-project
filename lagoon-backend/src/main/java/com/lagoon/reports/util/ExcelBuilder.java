package com.lagoon.reports.util;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.springframework.core.io.ClassPathResource;

/**
 * @author mltolentino
 *
 */
public class ExcelBuilder extends ExportToFileProtocol {

	@Override
	public void build(ExcelDataInput input) {
		FileInputStream inputStream = null;
		FileInputStream imageInputStream = null;
		ByteArrayOutputStream outputStream = null;
		HSSFWorkbook workbook = null;
		ClassPathResource excelTemplate = null;
		ClassPathResource logoImage = null;

		try {
			excelTemplate = new ClassPathResource("templates/user-management-template.xls");
			logoImage = new ClassPathResource("templates/ims-default-logo.png");

			Path path = Paths.get(excelTemplate.getFile().getAbsolutePath());
			Path imagePath = Paths.get(logoImage.getFile().getAbsolutePath());

			inputStream = new FileInputStream(path.toString());
			imageInputStream = new FileInputStream(imagePath.toString());

			NPOIFSFileSystem fs = new NPOIFSFileSystem(inputStream);

			workbook = new HSSFWorkbook(fs);
			HSSFSheet sheet = workbook.getSheetAt(0);
			sheet.setDefaultColumnWidth(30);

			HSSFCellStyle style = WorkbookConstants.createBorderCellStyle(workbook, HorizontalAlignment.CENTER,
					FillPatternType.SOLID_FOREGROUND, BorderStyle.THIN);
			style.setFont(WorkbookConstants.createFont(workbook, "Candara", true, IndexedColors.BLACK.getIndex(),
					(short) 11));

			insertImage(workbook, sheet, imageInputStream);
			createHeader(workbook, sheet);
			generatedInfoHeader(sheet, workbook, input.getMetadata(), input.getDataInputs().size());
			createContentHeader(sheet, style, workbook, input.getDataInputs());
			createContent(workbook, sheet, input.getDataInputs());

			outputStream = new ByteArrayOutputStream();
			workbook.write(outputStream);

			input.setExportedFile(outputStream.toByteArray());

			inputStream.close();
			outputStream.close();
			workbook.close();
			fs.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void insertImage(HSSFWorkbook workbook, HSSFSheet sheet, FileInputStream imageInputStream) {
		try {
			sheet.createRow(0);
//            sheet.addMergedRegion(new CellRangeAddress(0, // first row (0-based)
//                    4, // last row (0-based)
//                    0, // first column (0-based)
//                    5 // last column (0-based)
//            ));

			byte[] imageBytes = IOUtils.toByteArray(imageInputStream);
			int pictureureIdx = workbook.addPicture(imageBytes, Workbook.PICTURE_TYPE_PNG);

			Drawing<?> drawing = sheet.createDrawingPatriarch();
			ClientAnchor anchor = drawing.createAnchor(100, 100, 25, 50, 0, 0, 2, 4);
			anchor.setAnchorType(ClientAnchor.AnchorType.DONT_MOVE_AND_RESIZE);

			Picture picture = drawing.createPicture(anchor, pictureureIdx);
			picture.resize(0.55, 1);

			imageInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void createHeader(HSSFWorkbook workbook, HSSFSheet sheet) {
		sheet.createRow(1);
		sheet.addMergedRegion(new CellRangeAddress(5, // first row (0-based)
				5, // last row (0-based)
				0, // first column (0-based)
				3 // last column (0-based)
		));
	}

	private void generatedInfoHeader(HSSFSheet sheet, HSSFWorkbook workbook, Map<String, String> headers,
			int lastColumn) {
		int rowNum = sheet.getLastRowNum();
		for (Map.Entry<String, String> header : headers.entrySet()) {
			WorkbookConstants.createMetaHeaderCell(workbook, sheet, header.getKey(), rowNum, 0);
			WorkbookConstants.createMetaHeaderCellValue(workbook, sheet, header.getValue(), rowNum, 1);
			WorkbookConstants.createMetaHeaderCellValue(workbook, sheet, "", rowNum, 2);
			WorkbookConstants.createMetaHeaderCellValue(workbook, sheet, "", rowNum, 3);
			rowNum++;
		}
		sheet.createRow(rowNum);
		sheet.addMergedRegion(new CellRangeAddress(sheet.getLastRowNum(), // first row (0-based)
				sheet.getLastRowNum(), // last row (0-based)
				0, // first column (0-based)
				lastColumn// last column (0-based)
		));
	}

	/********************** Content Header *********************************/
	private void createContentHeader(HSSFSheet sheet, HSSFCellStyle style, HSSFWorkbook workbook,
			List<DataInput> inputs) {
		HSSFRow header = sheet.createRow(sheet.getLastRowNum() + 1);

		for (DataInput headerValue : inputs) {
			header.createCell(inputs.indexOf(headerValue)).setCellValue(headerValue.getDataHeader());
			header.getCell(inputs.indexOf(headerValue)).setCellStyle(style);
		}
	}

	/*************** Excel Content ********************/
	private void createContent(HSSFWorkbook workbook, HSSFSheet sheet, List<DataInput> inputs) {
		try {
			HSSFCellStyle style = WorkbookConstants.createBorderCellStyleDefault(workbook, HorizontalAlignment.CENTER,
					BorderStyle.THIN);
			HSSFFont fontContent = WorkbookConstants.createFont(workbook, "Candara", false,
					IndexedColors.BLACK.getIndex(), (short) 11);
			style.setFont(fontContent);

			int firstRowForData = sheet.getLastRowNum() + 1;

			for (DataInput input : inputs) {
				int currentRow = firstRowForData;
				for (String data : input.getDataRows()) {
					HSSFRow userRow = inputs.indexOf(input) == 0 ? sheet.createRow(currentRow)
							: sheet.getRow(currentRow);
					userRow.createCell(inputs.indexOf(input)).setCellValue(data);
					userRow.getCell(inputs.indexOf(input)).setCellStyle(style);
					currentRow++;
				}
			}
			for (int i = 0; i < inputs.size(); i++) {
				sheet.autoSizeColumn(i);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}