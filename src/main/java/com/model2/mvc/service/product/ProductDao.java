package com.model2.mvc.service.product;

import com.model2.mvc.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends JpaRepository<ProductEntity, Integer> {

	Page<ProductEntity> findByProdNameContaining(String prodName, Pageable pageable);

}
