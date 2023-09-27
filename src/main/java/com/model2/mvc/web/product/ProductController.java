//package com.model2.mvc.web.product;
//
//import com.model2.mvc.common.Page;
//import com.model2.mvc.common.Search;
//import com.model2.mvc.service.domain.Product;
//import com.model2.mvc.service.product.ProductService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//
//import jakarta.servlet.http.HttpServletRequest;
//import java.io.File;
//import java.util.Map;
//
//
//@Controller
//@RequestMapping("/product/*")
//public class ProductController {
//
//	///Field
//	@Autowired
//	@Qualifier("productService")
//	private ProductService productService;
//
//	public ProductController(){
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
//	@RequestMapping(value = "addProduct", method = RequestMethod.POST)
//	public String addProduct( @ModelAttribute("product") Product product, @RequestParam("uploadFile") MultipartFile[] uploadFile) throws Exception {
//
//		System.out.println("/product/addproduct : POST");
//		//Business Logic
//		String MultifileName = "";
//
//		for(int i=0; i<uploadFile.length ; i++) {
//			String fileName = uploadFile[i].getOriginalFilename();
//			File saveFile = new File(imgPath, fileName);
//			uploadFile[i].transferTo(saveFile);
//			if(i==0) {
//				MultifileName += fileName;
//			}else {
//				MultifileName += ","+fileName;
//			}
//
//		}
//		System.out.println(MultifileName);
//
//		product.setManuDate(product.getManuDate().replace("-", ""));
//		product.setFileName(MultifileName);
//		System.out.println(product);
//		productService.addProduct(product);
//
//		return "forward:/product/addProduct.jsp";
//	}
//
//	@RequestMapping(value = "getProduct", method = RequestMethod.GET)
//	public String getProduct( @RequestParam("prodNo") int prodNo , Model model ) throws Exception {
//
//		System.out.println("/product/getProduct : GET");
//		Product product = productService.getProduct(prodNo);
//		model.addAttribute("product", product);
//
//		return "forward:/product/getProduct.jsp";
//	}
//
//	@RequestMapping(value = "updateProduct", method = RequestMethod.GET)
//	public String updateProduct( @RequestParam("prodNo") int prodNo , Model model ) throws Exception{
//
//		System.out.println("/product/updateProductView : GET");
//		Product product = productService.getProduct(prodNo);
//		model.addAttribute("product", product);
//
//		return "forward:/product/updateProductView.jsp";
//	}
//
//	@RequestMapping(value = "updateProduct", method = RequestMethod.POST)
//	public String updateProduct( @ModelAttribute("product") Product product , Model model) throws Exception{
//
//		System.out.println("/product/updateProduct : POST");
//		productService.updateProduct(product);
//
//		return "redirect:/product/getProduct?prodNo="+product.getProdNo()+"&menu=ok";
//	}
//
//	@RequestMapping(value = "listProduct")
//	public String listProduct( @ModelAttribute("search") Search search , Model model , HttpServletRequest request) throws Exception{
//
//		System.out.println("/product/listProduct : GET / POST");
//
//		if(search.getCurrentPage() ==0 ){
//			search.setCurrentPage(1);
//		}
//		search.setPageSize(pageSize);
//
//		Map<String , Object> map=productService.getProductList(search);
//
//		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
//		System.out.println(resultPage);
//
//		model.addAttribute("list", map.get("list"));
//		model.addAttribute("resultPage", resultPage);
//		model.addAttribute("search", search);
//
//		return "forward:/product/listProduct.jsp";
//	}
//}