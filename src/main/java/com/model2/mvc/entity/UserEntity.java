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
@Entity(name="users")
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

    @Column
    private Date regDate;

}
