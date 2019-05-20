package com.lagoon.util;

import java.util.ArrayList;
import java.util.List;

public class PageableTable<T extends ArrayList> {
	
    private List<T> content = new ArrayList<T>();
    private boolean first;
    private boolean last;
    private int number;
    private int numberOfElements;
    private int size;
//  private sort: null
    private int totalElements;
    private int totalPages;
    private int[] pageNumber = {};
    
	public PageableTable(List<T> content, boolean first, boolean last, int number, int numberOfElements, int size,
			int totalElements, int totalPages, int[] pageNumber) {
		super();
		this.content = content;
		this.first = first;
		this.last = last;
		this.number = number;
		this.numberOfElements = numberOfElements;
		this.size = size;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
		this.setPageNumber(pageNumber);
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public boolean isLast() {
		return last;
	}

	public void setLast(boolean last) {
		this.last = last;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getNumberOfElements() {
		return numberOfElements;
	}

	public void setNumberOfElements(int numberOfElements) {
		this.numberOfElements = numberOfElements;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(int totalElements) {
		this.totalElements = totalElements;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int[] getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int[] pageNumber) {
		this.pageNumber = pageNumber;
	}
}
