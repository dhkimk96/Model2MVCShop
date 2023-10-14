package com.model2.mvc.web.purchase;

import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/purchase/*")
public class PurchaseController {

	///Field
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;

	public PurchaseController(){
		System.out.println(this.getClass());
	}

	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;

	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;

	@RequestMapping(value = "addPurchaseView", method = RequestMethod.GET)
	public ModelAndView addPurchaseView(@RequestParam("prodNo") int ProdNo, HttpSession session) throws Exception {

		System.out.println("/purchase/addPurchaseView : GET");
		//Business Logic
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("user", session.getAttribute("user"));
		modelAndView.addObject("product", productService.getProduct(ProdNo));
		modelAndView.setViewName("forward:/purchase/addPurchaseView.jsp");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "addPurchase", method = RequestMethod.POST)
	public ModelAndView addPurchase( @ModelAttribute("purchase") Purchase purchase,
									@RequestParam("prodNo") int ProdNo, @RequestParam("buyerId") String buyerId ) throws Exception {

		System.out.println("/purchase/addPurchase : POST");
		//Business Logic
		purchase.setBuyer(userService.getUser(buyerId));
		purchase.setPurchaseProd(productService.getProduct(ProdNo));
		purchase.setTranCode("100");
		
		purchaseService.addPurchase(purchase);

		return new ModelAndView("forward:/purchase/addPurchasejsp", "purchase", purchase);
	}

//	@RequestMapping(value = "getPurchase", method = RequestMethod.GET)
//	public ModelAndView getPurchase( @RequestParam("prodNo") int prodNo , Model model ) throws Exception {
//
//		System.out.println("/purchase/getProduct : GET");
//		//Business Logic
//		Purchase purchase = purchaseService.getPurchase(prodNo);
//		// Model �� View ����
//		model.addAttribute("purchase", purchase);
//
//		return "forward:/purchase/getProduct.jsp";
//	}
//
//	@RequestMapping(value = "updatePurchase", method = RequestMethod.GET)
//	public ModelAndView updatePurchase( @RequestParam("prodNo") int prodNo , Model model ) throws Exception{
//
//		System.out.println("/purchase/updateProductView : GET");
//		//Business Logic
//		Purchase purchase = purchaseService.getProduct(prodNo);
//		// Model �� View ����
//		model.addAttribute("purchase", purchase);
//
//		return "forward:/purchase/updateProductView.jsp";
//	}
//
//	@RequestMapping(value = "updateProduct", method = RequestMethod.POST)
//	public ModelAndView updatePurchase( @ModelAttribute("purchase") Purchase purchase , Model model) throws Exception{
//
//		System.out.println("/purchase/updatePurchase : POST");
//		//Business Logic
//		purchaseService.updatePurchase(purchase);
//
//		return "redirect:/purchase/getProduct?prodNo="+product.getProdNo()+"&menu=ok";
//	}
//
//	@RequestMapping(value = "listPurchase")
//	public ModelAndView listPurchase( @ModelAttribute("search") Search search , Model model , HttpServletRequest request) throws Exception{
//
//		System.out.println("/purchase/listPurchase : GET / POST");
//
//		if(search.getCurrentPage() ==0 ){
//			search.setCurrentPage(1);
//		}
//		search.setPageSize(pageSize);
//
//		// Business logic ����
//		Map<String , Object> map=purchaseService.getPurchaseList(search);
//
//		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
//		System.out.println(resultPage);
//
//		// Model �� View ����
//		model.addAttribute("list", map.get("list"));
//		model.addAttribute("resultPage", resultPage);
//		model.addAttribute("search", search);
//
//		return "forward:/purchase/listPurchase.jsp";
//	}
}