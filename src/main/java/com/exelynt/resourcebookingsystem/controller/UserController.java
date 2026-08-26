package com.exelynt.resourcebookingsystem.controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.exelynt.resourcebookingsystem.service.UserService;
import com.exelynt.resourcebookingsystem.entity.User;
import com.exelynt.resourcebookingsystem.enums.Role;

@Controller
public class UserController {
	 @Autowired
	  private UserService userService;
	 
	 
	 @GetMapping("/register")
	 public String showRegisterPage() {
	     return "register";
	 }
	 
	 @PostMapping("/register")
	 public String registerUser(@RequestParam String name,@RequestParam String email,@RequestParam String password){
		 User user = new User(name, email, password, Role.USER);
		 userService.registerUser(user);
		 return "home";

	 }
	 
	 @GetMapping("/login")
	 public String showLoginPage() {
	     return "login";
	 }
	 
	 @PostMapping("/login")
	 public String loginUser(@RequestParam String email,@RequestParam String password) {

	     User user = userService.findByEmail(email);

	     if (user == null) {
	         return "login";
	     }

	     if (!user.getPassword().equals(password)) {
	         return "login";
	     }

	     if(user.getRole() == Role.ADMIN) {
	    	    return "admin-dashboard";
	    	}

	    	return "user-dashboard";
	 }
	
}
