package com.model2.mvc.common;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Search {


	private int currentPage;
	private String searchCondition;
	private String searchKeyword;
	private String orderBy;
	private int pageSize;
	private int endRowNum;
	private int startRowNum;

}