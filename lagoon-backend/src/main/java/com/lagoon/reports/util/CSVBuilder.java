package com.lagoon.reports.util;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import com.lagoon.dto.UserDTO;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

public class CSVBuilder extends ExportToFileProtocol {

	@Override
	public void build(CSVDataInput input) {
		FileInputStream inputStream = null;
		FileInputStream imageInputStream = null;
		ByteArrayOutputStream outputStream = null;
		try {
//			ColumnPositionMappingStrategy<CSVInput> mapStrategy = new ColumnPositionMappingStrategy<CSVInput>();
//			mapStrategy.setType(CSVInput.class);
			// mapStrategy.generateHeader();

//			String[] columns = new String[] { "id", "name", "population" };
//			mapStrategy.setColumnMapping(columns);
//			mapStrategy.setType(CSVInput.class);
			//mapStrategy.generateHeader();

			outputStream = new ByteArrayOutputStream();
			Writer writer = new FileWriter("sdadsadas.csv");
			OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream);

			StatefulBeanToCsvBuilder<UserDTO> btcsv = new StatefulBeanToCsvBuilder<UserDTO>(writer);
			 StatefulBeanToCsv<UserDTO> beanWriter =    btcsv.build();

			 List<UserDTO> userdto = new ArrayList<>();
			 UserDTO aa = new UserDTO();
			 aa.setFirstName("mc kinley");
			 userdto.add(aa);
			 
			 ByteArrayOutputStream baos = new ByteArrayOutputStream();
			 CSVWriter writers = new CSVWriter(new OutputStreamWriter(baos), ',',
			 CSVWriter.DEFAULT_QUOTE_CHARACTER, CSVWriter.NO_ESCAPE_CHARACTER, "\n");
			 String[] entries =  {}; //input.getDataInputs().toArray(); 
			 writers.writeNext(entries);
			 writers.close();
			 
			// beanWriter.write(userdto);			
			//input.setExportedFile();
			outputStream.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
