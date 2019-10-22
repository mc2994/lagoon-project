package com.lagoon.reports.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;

import com.lagoon.service.UserService;

public class CSVBuilder {

	@Autowired
	private UserService userService;

	public void build(PrintWriter pwriter) throws IOException {
		CSVPrinter csvPrinter = new CSVPrinter(pwriter,
				CSVFormat.DEFAULT.withHeader("ID", "Name", "Designation", "Company"));
		try {

			csvPrinter.printRecord("1", "Sundar Pichai ♥", "CEO", "Google");
			csvPrinter.printRecord("2", "Satya Nadella", "CEO", "Microsoft");
			csvPrinter.printRecord("3", "Tim cook", "CEO", "Apple");

			csvPrinter.printRecord(Arrays.asList("4", "Mark Zuckerberg", "CEO", "Facebook"));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pwriter.flush();
			pwriter.close();
			csvPrinter.close();
		}
	}
}
