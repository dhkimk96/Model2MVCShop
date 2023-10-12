package com.model2.mvc.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.sql.Date;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Table(name = "transcation")
@Entity
public class PurchaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tranNo;

    private String paymentOption;

    private String receiverName;

    private String receiverPhone;

    private String divyAddr;

    private String divyRequest;

    private String tranCode;

    private Date orderDate;

    private Date divyDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "prod_id")
    private ProductEntity productEntity;
}
