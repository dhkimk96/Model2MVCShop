package com.model2.mvc.web.user;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/user/*")
public class UserController {

	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;

	public UserController(){
		System.out.println(this.getClass());
	}

	@Value("${pageUnit}")
	int pageUnit;

	@Value("${pageSize}")
	int pageSize;

	@RequestMapping( value="addUser", method=RequestMethod.GET )
	public String addUser() throws Exception{
		System.out.println("/user/addUser : GET");
		return "redirect:/user/addUserView.jsp";
	}

	@RequestMapping( value="addUser", method=RequestMethod.POST )
	public String addUser( @ModelAttribute("user") User user ) throws Exception {
		System.out.println("/user/addUser : POST");
		//Business Logic
		userService.addUser(user);
		return "redirect:/user/loginView.jsp";
	}


	@RequestMapping( value="getUser", method=RequestMethod.GET )
	public String getUser( @RequestParam("userId") String userId , Model model ) throws Exception {
		System.out.println("/user/getUser : GET");
		User user = userService.getUser(userId);
		model.addAttribute("user", user);
		return "forward:/user/getUser.jsp";
	}


	@RequestMapping( value="updateUser", method=RequestMethod.GET )
	public String updateUser( @RequestParam("userId") String userId , Model model ) throws Exception{
		System.out.println("/user/updateUser : GET");
		User user = userService.getUser(userId);
		model.addAttribute("user", user);
		return "forward:/user/updateUser.jsp";
	}

	@RequestMapping( value="updateUser", method=RequestMethod.POST )
	public String updateUser( @ModelAttribute("user") User user , Model model , HttpSession session) throws Exception{
		System.out.println("/user/updateUser : POST");
		userService.updateUser(user);
		String sessionId=((User)session.getAttribute("user")).getUserId();
		if(sessionId.equals(user.getUserId())){
			session.setAttribute("user", user);
		}

		return "redirect:/user/getUser?userId="+user.getUserId();
	}


	@RequestMapping( value="login", method=RequestMethod.GET )
	public String login() throws Exception{
		System.out.println("/user/logon : GET");
		return "redirect:/user/loginView.jsp";
	}

	@RequestMapping( value="login", method=RequestMethod.POST )
	public String login(@ModelAttribute("user") User user , HttpSession session ) throws Exception{
		System.out.println("/user/login : POST");
		User dbUser=userService.getUser(user.getUserId());
		if( user.getPassword().equals(dbUser.getPassword())){
			session.setAttribute("user", dbUser);
		}
		return "redirect:/index.jsp";
	}


	@RequestMapping( value="logout", method=RequestMethod.GET )
	public String logout(HttpSession session ) throws Exception{
		System.out.println("/user/logout : GET");
		session.invalidate();
		return "redirect:/index.jsp";
	}


	@RequestMapping( value="checkDuplication", method=RequestMethod.POST )
	public String checkDuplication( @RequestParam("userId") String userId , Model model ) throws Exception{
		System.out.println("/user/checkDuplication : POST");
		boolean result=userService.checkDuplication(userId);
		model.addAttribute("result", result);
		model.addAttribute("userId", userId);
		return "forward:/user/checkDuplication.jsp";
	}


	@RequestMapping( value="listUser" )
	public String listUser( @ModelAttribute("search") Search search , Model model) throws Exception{

		System.out.println("/user/listUser : GET / POST");

		if(search.getCurrentPage()==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		if(search.getOrderBy() == null){
			search.setOrderBy("userId");
		}
		int startRowNum = search.getCurrentPage() * pageSize - pageSize+1;
		int endRowNum = startRowNum + pageSize - 1;
		search.setStartRowNum(startRowNum);
		search.setEndRowNum(endRowNum);
		// Business logic 수행
		Map<String , Object> map=userService.getUserList(search);

		Page resultPage = new Page( search.getCurrentPage(), (Integer) map.get("totalCount"), pageUnit, pageSize);
		System.out.println(resultPage);
		// Model 과 View 연결
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);

		System.out.println("카운트: "+resultPage.getTotalCount());

		return "/user/listUser.jsp";
	}
}