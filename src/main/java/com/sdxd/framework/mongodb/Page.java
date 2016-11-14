package com.sdxd.framework.mongodb;

import java.util.List;

import lombok.Data;

@Data
public class Page<T> {

	private Long total;
	private Integer pageNumber;
	private Integer pageSize;
	private List<T> rows;
	
	
}
