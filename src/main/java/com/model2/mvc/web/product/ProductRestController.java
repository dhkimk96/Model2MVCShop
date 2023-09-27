//package com.model2.mvc.web.product;
//
//import com.model2.mvc.common.Search;
//import com.model2.mvc.service.domain.Product;
//import com.model2.mvc.service.product.ProductService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.bind.annotation.*;
//
//import jakarta.servlet.http.HttpServletRequest;
//import java.util.List;
//import java.util.Map;
//
//
//@RestController
//@RequestMapping("/product/*")
//public class ProductRestController {
//
//	///Field
//	@Autowired
//	@Qualifier("productService")
//	private ProductService productService;
//
//	public ProductRestController(){
//		System.out.println(this.getClass());
//	}
//
//	@Value("${pageUnit}")
//	int pageUnit;
//
//	@Value("${pageSize}")
//	int pageSize;
//
//	@Value("${imgPath}")
//	String imgPath;
//
//	@RequestMapping(value = "json/addProduct", method = RequestMethod.POST)
//	public Product addProduct( @RequestBody Product product ) throws Exception {
//
//		System.out.println("/product/json/addproduct : POST");
//		//Business Logic
//		 productService.addProduct(product);
//
//		return product;
////		return productService.getProduct(product.getProdNo());
//	}
//
//	@RequestMapping(value = "json/getProduct/{prodNo}", method = RequestMethod.GET)
//	public Product getProduct( @PathVariable int prodNo) throws Exception {
//
//		System.out.println("/product/json/getProduct : GET");
//		//Business Logic
//		return productService.getProduct(prodNo);
//	}
//
//	@RequestMapping(value = "json/updateProduct", method = RequestMethod.POST)
//	public Product updateProduct( @RequestBody Product product) throws Exception{
//
//		System.out.println("/product/json/updateProduct : POST");
//		//Business Logic
//		productService.updateProduct(product);
//
//		return productService.getProduct(product.getProdNo());
//	}
//
//	@RequestMapping(value = "json/listProduct")
//	public Map<String, Object> listProduct( @RequestBody Search search , HttpServletRequest request) throws Exception{
//
//		System.out.println("/product/json/listProduct : GET / POST");
//
//		if(search.getCurrentPage() ==0 ){
//			search.setCurrentPage(1);
//		}
//		search.setPageSize(pageSize);
//
//		String message = "ok";
//		List<Product> list = (List<Product>) map.get("list");
//		if(list.size()<1) {
//			message = "no";
//		}
//		map.put("message", message);
//		System.out.println(map);
//
//
//		return maap;
//	}
//
//	@RequestMapping(value = "json/Autocomplete" ,method = RequestMethod.GET)
//	public List<String> Autocomplete() throws Exception {
//		System.out.println("json/Autocomplete/");
//
//		List<String> data = productService.getAutoCompleteProdName();
//
//		System.out.println(data);
//
//		return data;
//	}
//
//
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
