package com.ydm.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ydm.pojo.User;
import com.ydm.service.UserService;
import com.ydm.util.BaseRespObject;


@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	@RequestMapping("/login")
	@ResponseBody
	public User login() {
		User user = userService.findUser();
		return user;
	}
	
	@RequestMapping("/User")
	@ResponseBody
	public BaseRespObject GetUser(String login,String pwd,HttpServletRequest request,HttpServletResponse response) throws Exception {
		BaseRespObject baseRespObject = new BaseRespObject();
		User user = userService.findUser();
		if(login.equals(user.getName())&&pwd.equals(user.getPassword())) {
			baseRespObject.setStatus(BaseRespObject.STATUS_1);
			baseRespObject.setMessage("success");
			return baseRespObject;
		}else{
			baseRespObject.setStatus(BaseRespObject.STATUS_0);
			baseRespObject.setMessage("defeat");
			return baseRespObject;
		}
	}
	
    
}
