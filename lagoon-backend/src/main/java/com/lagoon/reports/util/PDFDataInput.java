package com.lagoon.reports.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PDFDataInput {

    private byte[] exportedFile;
    private List<PDFInput> dataInputs;
    private Map<String, String> metadata;
    private HashMap<List<String>, HashMap<Integer, String>> data;

    public DataInput createDataInput(String header, List<String> values) {
        return new DataInput(header, values);
    }

    public void initDataInput(String... headers) {
        this.setDataInputs(new ArrayList<PDFInput>());
        ;
        for (String header : headers) {
            PDFInput input = new PDFInput(header, new ArrayList<String>());
            this.getDataInputs().add(input);
        }
    }

    public void addDataToRow(String headerName, String value) {
        for (PDFInput input : this.getDataInputs()) {
            if (input.getDataHeader().equalsIgnoreCase(headerName)) {
                input.getDataRows().add(value);
            }
        }
    }

    public void addDataToRow(Integer columnIndex, String value) {
        if (this.getDataInputs().get(columnIndex) != null) {
            PDFInput data = this.getDataInputs().get(columnIndex);
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

    public List<PDFInput> getDataInputs() {
        return dataInputs;
    }

    public void setDataInputs(List<PDFInput> dataInputs) {
        this.dataInputs = dataInputs;
    }
}

class PDFInput {
    private String dataHeader;
    private List<String> dataRows;

    public PDFInput(String header, List<String> rows) {
        this.setDataHeader(header);
        this.setDataRows(rows);
    }

    public String getDataHeader() {
        return dataHeader;
    }

    public void setDataHeader(String dataHeader) {
        this.dataHeader = dataHeader;
    }

    public List<String> getDataRows() {
        return dataRows;
    }

    public void setDataRows(List<String> dataRows) {
        this.dataRows = dataRows;
    }
}