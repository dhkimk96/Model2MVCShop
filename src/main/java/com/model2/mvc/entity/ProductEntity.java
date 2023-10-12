package com.model2.mvc.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Date;
import java.util.List;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Table(name = "product")
@Entity
public class ProductEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int prodNo;

    @Column(nullable = false)
    private String prodName;

    @Column( nullable = false)
    private String prodDetail;

    @Column
    private Date manuDate;

    @Column
    private int price;

    @Column(name = "image_file")
    private String fileName;

    @CreatedDate
    @Column(updatable = false)
    private Date regDate;

    @OneToMany(mappedBy = "productEntity",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PurchaseEntity> purchaseEntities;
}

