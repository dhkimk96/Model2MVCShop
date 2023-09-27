//package com.model2.mvc.service.product.impl;
//
//import com.model2.mvc.common.Search;
//import com.model2.mvc.service.domain.Product;
//import com.model2.mvc.service.product.ProductDao;
//import com.model2.mvc.service.product.ProductService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Service("productService")
//@Transactional
//public class ProductServiceImpl implements ProductService {
//
//	///Field
//	@Autowired
//	@Qualifier("productDao")
//	private ProductDao productDao;
//	public void setProductDao(ProductDao productDao) {
//		this.productDao = productDao;
//	}
//
//	public ProductServiceImpl() {
//		System.out.println(this.getClass());
//	}
//
//	@Override
//	public void addProduct(Product product) throws Exception {
//		productDao.addProduct(product);
//	}
//
//	@Override
//	public Product getProduct(int prodNo) throws Exception {
//		return productDao.getProduct(prodNo);
//	}
//
//	@Override
//	public Map<String, Object> getProductList(Search search) throws Exception {
//		List<Product> list= productDao.getProductList(search);
//		int totalCount = productDao.getTotalCount(search);
//		Map<String, Object> map = new HashMap<>();
//		map.put("list", list );
//		map.put("totalCount", new Integer(totalCount));
//
//		return map;
//	}
//
//	@Override
//	public void updateProduct(Product Product) throws Exception {
//		productDao.updateProduct(Product);
//
//	}
//
//	@Override
//	public List<String> getAutoCompleteProdName() throws Exception {
////	    List<Product> prodList = productDao.getAutoCompleteProdName();
////	    List<String> autoCompleteList = new ArrayList<>();
////
////	    for(int i=0;i<prodList.size();i++) {
////	    	autoCompleteList.add(prodList.get(i).getProdName());
////	    }
////
////	    System.out.println(autoCompleteList);
//
////		return autoCompleteList;
//		return null;
//	}
//
//}
