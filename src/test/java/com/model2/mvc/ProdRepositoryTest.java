package com.model2.mvc;

import com.model2.mvc.entity.ProductEntity;
import com.model2.mvc.service.product.ProductDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;
import java.sql.Date;

@SpringBootTest
public class ProdRepositoryTest {

    @Autowired
    ProductDao productDao;

    @Test
    public void InsertDummies() {

        IntStream.rangeClosed(1, 10).forEach(i -> {
            ProductEntity productEntity = ProductEntity.builder()
                    .prodName("Sample..." + i)
                    .prodDetail("Sample..." + i)
                    .price(i)
                    .manufactureDay("20230909")
                    .fileName("sample_image_" + i + ".jpg")
                    .regDate(new Date(System.currentTimeMillis()))
                    .build();
            //Create!
            productDao.save(productEntity);
        });
    }
}
