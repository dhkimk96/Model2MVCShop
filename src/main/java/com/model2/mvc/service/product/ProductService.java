package com.model2.mvc.service.product;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;

import java.util.Map;

public interface ProductService {

	public Product addProduct(Product product) throws Exception;

	public Product getProduct(int prodNo) throws Exception;

	public Map<String, Object> getProductList(Search search) throws Exception;

	public int updateProduct(Product Product) throws Exception;

//	public List<String> getAutoCompleteProdName() throws Exception;

}
