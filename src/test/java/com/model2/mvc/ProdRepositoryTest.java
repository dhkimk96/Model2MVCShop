package com.model2.mvc;

import com.model2.mvc.common.Search;
import com.model2.mvc.entity.ProductEntity;
import com.model2.mvc.mapper.ProductMapper;
import com.model2.mvc.service.product.ProductDao;
import com.model2.mvc.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.Map;
import java.util.stream.IntStream;

@SpringBootTest
public class ProdRepositoryTest {

    @Autowired
    ProductDao productDao;

    @Autowired
    ProductService productService;

    @Autowired
    ProductMapper productMapper;

    //@Test
    public void InsertDummies() {

        IntStream.rangeClosed(1, 10).forEach(i -> {
            ProductEntity productEntity = ProductEntity.builder()
                    .prodName("Sample..." + i)
                    .prodDetail("Sample..." + i)
                    .price(i)
                    .fileName("sample_image_" + i + ".jpg")
                    .regDate(new Date(System.currentTimeMillis()))
                    .build();
            //Create!
            productDao.save(productEntity);
        });
    }
    //@Test
    public void testGetProductList() throws Exception{
        Search search = Search.builder()
                .orderBy("prodNo")
                .pageSize(5)
                .currentPage(1)
                .build();
        Map<String, Object> map = productService.getProductList(search);
        System.out.println(map);
    }
}
