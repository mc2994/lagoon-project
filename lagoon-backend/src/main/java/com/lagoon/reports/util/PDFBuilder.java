package com.lagoon.reports.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.core.io.ClassPathResource;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFBuilder extends ExportToFileProtocol {

	@Override
	public void build(PDFDataInput input) {
		ByteArrayOutputStream outputStream = null;
		Document document = new Document(PageSize.LETTER);
		ClassPathResource ims_logo = null;

		PdfPTable table = PDFConstants.createtable(4, 100, Element.ALIGN_CENTER, Element.ALIGN_MIDDLE);
		PdfPTable hostNameTable = PDFConstants.createtable(3, 100, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE);
		PdfPTable space = new PdfPTable(1);

		float[] columnWidth = { 2f, 2.5f, 2f, 1f };

		Font boldFont = PDFConstants.createFont(12, Font.BOLD);
		Font regularFont = PDFConstants.createFont(12, Font.NORMAL);

		try {
			outputStream = new ByteArrayOutputStream();
			PdfWriter.getInstance(document, outputStream);
			document.open();
			ims_logo = new ClassPathResource("templates/ims-default-logo.png");
			Image img = Image.getInstance(ims_logo.getFile().getAbsolutePath());
			img.scaleAbsolute(140f, 80f);
			document.add(img);

			generatedInfoHeader(hostNameTable, space, boldFont, regularFont, input.getMetadata());
			createContentHeader(table, boldFont, document, input.getDataInputs());
			createContent(table, regularFont, document, input.getDataInputs());

			document.add(hostNameTable);
			document.add(space);
			document.add(table);
			table.setTotalWidth(columnWidth);
			document.close();

			input.setExportedFile(outputStream.toByteArray());
			outputStream.close();

		} catch (IOException | DocumentException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void generatedInfoHeader(PdfPTable hostNameTable, PdfPTable space, Font boldFont, Font regularFont,
			Map<String, String> headers) {
		for (Map.Entry<String, String> header : headers.entrySet()) {
			PdfPCell hostCell = new PdfPCell(new Phrase(header.getKey(), boldFont));
			PDFConstants.createCell(hostCell, Rectangle.NO_BORDER, Element.ALIGN_LEFT);
			hostNameTable.addCell(hostCell);

			hostCell = new PdfPCell(new Phrase(header.getValue(), regularFont));
			PDFConstants.createCell(hostCell, Rectangle.NO_BORDER, Element.ALIGN_LEFT);
			hostNameTable.addCell(hostCell);

			hostCell = new PdfPCell(new Phrase(""));
			PDFConstants.createCell(hostCell, Rectangle.NO_BORDER, Element.ALIGN_LEFT);
			hostNameTable.addCell(hostCell);
		}

		space.setSpacingAfter(15f);

		PdfPCell hostCell = new PdfPCell(new Phrase(""));
		hostCell.setBorder(Rectangle.NO_BORDER);
		space.addCell(hostCell);
	}

	private void createContentHeader(PdfPTable table, Font boldFont, Document document, List<PDFInput> headers) {
		for (PDFInput header : headers) {
			table.addCell(new Phrase(header.getDataHeader(), boldFont));
		}
		table.setHeaderRows(1);

		PdfPCell[] cells = table.getRow(0).getCells();
		for (int j = 0; j < cells.length; j++) {
			cells[j].setBackgroundColor(BaseColor.LIGHT_GRAY);
			cells[j].setPadding(5);
		}
	}

	private void createContent(PdfPTable table, Font regularFont, Document document, List<PDFInput> inputs) {
		if (!inputs.isEmpty()) {
			for (int x = 0; x < inputs.get(0).getDataRows().size(); x++) {
				for (PDFInput input : inputs) {
					table.addCell(new Phrase(input.getDataRows().get(x), regularFont));
					table.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				}
			}
		}
	}
}