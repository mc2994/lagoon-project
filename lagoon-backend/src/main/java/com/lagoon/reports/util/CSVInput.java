package com.lagoon.reports.util;

import java.util.List;

public class CSVInput {
	
	private String dataHeader;
    private List<String> dataRows;

    public CSVInput(String header, List<String> rows) {
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
