package com.model2.mvc;

import com.model2.mvc.entity.UserEntity;
import com.model2.mvc.service.user.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;
import java.sql.Date;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserDao userDao;

    @Test
    public void InsertDummies() {

        IntStream.rangeClosed(1, 11).forEach(i -> {
            UserEntity userEntity = UserEntity.builder()
                    .userId("Sample..." + i)
                    .userName("Sample" + i)
                    .password("0" + i)
                    .email(i + "@" + i + ".com")
                    .regDate(new Date(System.currentTimeMillis()))
                    .build();
            //Create!
            userDao.save(userEntity);
        });
    }

    @Test
    public void DeleteDummies(){
        IntStream.rangeClosed(1, 10).forEach(i -> {
            UserEntity userEntity = UserEntity.builder()
                    .userId("Sample..." + i)
                    .build();
            //Create!
            userDao.delete(userEntity);
        });

    }

   // @Test
    public void FindAll(){
            UserEntity userEntity = UserEntity.builder()
                    .userId("admin")
                    .build();

            userDao.findAll();

    }
}
