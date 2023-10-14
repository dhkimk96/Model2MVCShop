package com.model2.mvc.service.product.impl;

import com.model2.mvc.common.Search;
import com.model2.mvc.entity.ProductEntity;
import com.model2.mvc.mapper.ProductMapper;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDao;
import com.model2.mvc.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service("productServiceImpl")
public class ProductServiceImpl implements ProductService {

	///Field
    private final ProductDao productDao;
    private final ProductMapper productMapper;

	@Override
	public Product addProduct(Product product) throws Exception {
		productDao.save(productMapper.productToProductEntity(product));
		return product;
	}

	@Override
	public Product getProduct(int prod_no) throws Exception {
		Optional<ProductEntity> productEntityOptional = productDao.findById(prod_no);
		return productEntityOptional.map(productMapper::productEntityToProduct).orElse(null);
	}

	@Override
	public Map<String, Object> getProductList(Search search) throws Exception {
		Sort sort = Sort.by(search.getOrderBy());
        Pageable pageable = PageRequest.of(search.getCurrentPage(), search.getPageSize(), sort);
        Page<ProductEntity> page;
        if(search.getSearchKeyword() == null || search.getSearchKeyword().isEmpty()){
            page = productDao.findAll(pageable);
        }else{
            page = productDao.findByProdNameContaining(search.getSearchKeyword(), pageable);
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", page.map(productMapper::productEntityToProduct).toList());
        map.put("count", page.getTotalPages());
        return map;
	}

	@Override
	public int updateProduct(Product product) throws Exception {
		if(productDao.findById(product.getProdNo()).isPresent()) {
			productDao.save(productMapper.productToProductEntity(product));
			return 1;
		}else{
			return 0;
		}
	}

//	@Override
//	public List<String> getAutoCompleteProdName() throws Exception {
//	    List<Product> prodList = productDao.getAutoCompleteProdName();
//	    List<String> autoCompleteList = new ArrayList<>();
//
//	    for(int i=0;i<prodList.size();i++) {
//	    	autoCompleteList.add(prodList.get(i).getProdName());
//	    }
//
//	    System.out.println(autoCompleteList);
//
//		return autoCompleteList;
//		return null;
//	}

}
