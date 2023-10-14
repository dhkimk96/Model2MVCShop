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
@Table(name = "users")
@Entity
public class UserEntity {

    @Id
    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Builder.Default
    private String role = "user";

    @Column
    private String ssn;

    @Column(name = "cell_phone")
    private String phone;

    @Column
    private String addr;

    @Column
    private String email;

    @CreatedDate
    @Column(updatable = false)
    private Date regDate;

    @OneToMany(mappedBy = "userEntity",fetch = FetchType.LAZY)
    private List<PurchaseEntity> purchaseEntities;
}
