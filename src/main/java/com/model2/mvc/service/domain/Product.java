package com.model2.mvc.service.domain;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Product {

	private int prodNo;
	private String prodName;
	private String prodDetail;
	private int price;
	private String manuDate;
	private String fileName;
	private String proTranCode;
	private Date regDate;
}