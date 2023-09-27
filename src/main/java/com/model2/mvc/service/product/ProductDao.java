package com.model2.mvc.service.product;

import com.model2.mvc.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends JpaRepository<ProductEntity, Integer> {

//	public void addProduct(Product product) throws Exception;
//
//	public Product getProduct(int prodNo) throws Exception;
//
//	public List<Product> getProductList(Search search) throws Exception;
//
//	public void updateProduct(Product product) throws Exception;
//
//	public int getTotalCount(Search search) throws Exception;

//	public List<Product> getAutoCompleteProdName() throws Exception;

}
