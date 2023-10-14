package com.model2.mvc.service.domain;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Purchase {

	private User buyer;
	private String divyAddr;
	private String divyDate;
	private String divyRequest;
	private Date orderDate;
	private String paymentOption;
	private Product purchaseProd;
	private String receiverName;
	private String receiverPhone;
	private String tranCode;
	private int tranNo;
}