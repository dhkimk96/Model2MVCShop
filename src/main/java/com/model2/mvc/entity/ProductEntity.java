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
@Entity(name="product")
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
    private String manufactureDay;

    @Column
    private int price;

    @Column(name = "image_file")
    private String fileName;

    @Column
    private Date regDate;

}
