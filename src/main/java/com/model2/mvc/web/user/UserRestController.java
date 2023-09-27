//package com.model2.mvc.web.user;
//
//import com.model2.mvc.common.Page;
//import com.model2.mvc.common.Search;
//import com.model2.mvc.service.domain.User;
//import com.model2.mvc.service.user.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.bind.annotation.*;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpSession;
//import java.util.List;
//import java.util.Map;
//
//
//@RestController
//@RequestMapping("/user/*")
//public class UserRestController {
//
//	///Field
//	@Autowired
//	@Qualifier("userService")
//	private UserService userService;
//
//	public UserRestController(){
//		System.out.println(this.getClass());
//	}
//
//	@Value("${pageUnit}")
//	int pageUnit;
//
//	@Value("${pageSize}")
//	int pageSize;
//
//	@RequestMapping( value="json/getUser/{userId}", method=RequestMethod.GET )
//	public User getUser( @PathVariable String userId ) throws Exception{
//
//		System.out.println("/user/json/getUser : GET"+userId);
//
//		//Business Logic
//		return userService.getUser(userId);
//	}
//
//	@RequestMapping( value="json/login", method=RequestMethod.POST )
//	public User login( 	@RequestBody User user,
//									HttpSession session ) throws Exception{
//
//		System.out.println("/user/json/login : POST");
//		//Business Logic
//		System.out.println("::"+user);
//		User dbUser=userService.getUser(user.getUserId());
//
//		if( user.getPassword().equals(dbUser.getPassword())){
//			session.setAttribute("user", dbUser);
//		}
//
//		return dbUser;
//	}
//
//	@RequestMapping( value="json/addUser", method=RequestMethod.POST )
//	public User addUser( @RequestBody User user ) throws Exception {
//
//		System.out.println("/user/json/addUser : POST");
//
//		userService.addUser(user);
//		//Business Logic
//		return userService.getUser(user.getUserId());
//	}
//
//	@RequestMapping( value="json/updateUser", method=RequestMethod.POST )
//	public User updateUser( @RequestBody User user,
//										HttpSession session) throws Exception{
//
//		System.out.println("/user/json/updateUser : POST");
//		//Business Logic
//		userService.updateUser(user);
//
////		String sessionId=((User)session.getAttribute("user")).getUserId();
////		if(sessionId.equals(user.getUserId())){
////			session.setAttribute("user", user);
////		}
//
//		return userService.getUser(user.getUserId());
//	}
//
//	@RequestMapping( value="json/logout/{userId}", method=RequestMethod.GET )
//	public User logout(HttpSession session ) throws Exception{
//		System.out.println("/user/json/logout : GET");
//
//		session.invalidate();
//
//		return (User)session.getAttribute("user");
//	}
//
//	@RequestMapping( value="json/checkDuplication", method=RequestMethod.POST )
//	public boolean checkDuplication( @RequestBody User user) throws Exception{
//
//		System.out.println("/user/json/checkDuplication : POST");
//
//		//Business Logic
//		boolean result=userService.checkDuplication(user.getUserId());
//
//		return result;
//	}
//
//	@RequestMapping( value="json/listUser" )
//	public Map<String, Object> listUser( @RequestBody Search search , HttpServletRequest request) throws Exception{
//
//		System.out.println("/user/json/listUser : GET / POST");
//
//		if(search.getCurrentPage() ==0 ){
//			search.setCurrentPage(1);
//		}
//
//		search.setPageSize(pageSize);
//
//		Map<String , Object> map=userService.getUserList(search);
//
//		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
//		System.out.println(resultPage);
//
//		return map;
//	}
//
//	@RequestMapping(value = "json/Autocomplete" ,method = RequestMethod.GET)
//	public List<String> Autocomplete() throws Exception {
//		System.out.println("json/Autocomplete");
//
//		List<String> data = userService.getAutoCompleteUser();
//
//		System.out.println(data);
//
//		return data;
//	}
//
//}