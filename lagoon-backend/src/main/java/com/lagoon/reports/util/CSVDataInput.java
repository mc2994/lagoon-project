package com.lagoon.reports.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVDataInput {

    private byte[] exportedFile;
    private List<CSVInput> dataInputs;
    private Map<String, String> metadata;
    private HashMap<List<String>, HashMap<Integer, String>> data;

    public CSVInput createDataInput(String header, List<String> values) {
        return new CSVInput(header, values);
    }

    public void initDataInput(String... headers) {
        this.setDataInputs(new ArrayList<CSVInput>());
        ;
        for (String header : headers) {
        	CSVInput input = new CSVInput(header, new ArrayList<String>());
            this.getDataInputs().add(input);
        }
    }

    public void addDataToRow(String headerName, String value) {
        for (CSVInput input : this.getDataInputs()) {
            if (input.getDataHeader().equalsIgnoreCase(headerName)) {
                input.getDataRows().add(value);
            }
        }
    }

    public void addDataToRow(Integer columnIndex, String value) {
        if (this.getDataInputs().get(columnIndex) != null) {
        	CSVInput data = this.getDataInputs().get(columnIndex);
            data.getDataRows().add(value);
        } else {
            throw new IllegalArgumentException("wrong column index specified.");
        }
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

    public HashMap<List<String>, HashMap<Integer, String>> getData() {
        return data;
    }

    public void setData(HashMap<List<String>, HashMap<Integer, String>> data) {
        this.data = data;
    }

    public byte[] getExportedFile() {
        return exportedFile;
    }

    public void setExportedFile(byte[] exportedFile) {
        this.exportedFile = exportedFile;
    }

    public List<CSVInput> getDataInputs() {
        return dataInputs;
    }

    public void setDataInputs(List<CSVInput> dataInputs) {
        this.dataInputs = dataInputs;
    }
}