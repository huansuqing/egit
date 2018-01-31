package com.e3.utils;

import java.io.Serializable;
import java.util.List;

public class PageResult implements Serializable{

	//总条数
	private Integer total;
	
	private List<?> rows;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
}
