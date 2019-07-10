package com.lagoon.reports.util;

import org.springframework.core.io.ClassPathResource;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class PDFConstants {

	public static Font createFont(int fontSize, int fontWeight) {
		ClassPathResource fontName = new ClassPathResource("templates/Candara.ttf");
		FontFactory.register(fontName.getPath(), "candara");
		Font font = FontFactory.getFont("candara", fontSize, fontWeight);
		return font;
	}

	public static PdfPTable createtable(int tableColumn, int tableWidth, int horizontalAlignment,
			int verticalAlignment) {
		PdfPTable table = new PdfPTable(tableColumn);
		table.setWidthPercentage(tableWidth);
		table.getDefaultCell().setHorizontalAlignment(horizontalAlignment);
		table.getDefaultCell().setVerticalAlignment(verticalAlignment);
		return table;
	}

	public static PdfPCell createCell(PdfPCell cell, int border, int horizontalAlignment) {
		cell.setBorder(border);
		cell.setHorizontalAlignment(horizontalAlignment);
		return cell;
	}
}