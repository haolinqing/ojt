package jp.co.ntt.ojt.app.user;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.ntt.ojt.app.user.UserForm.Login;

@Controller
@RequestMapping("login")
public class LoginController {
	
	@ModelAttribute
	public UserForm setUpForm() {
		return new UserForm();
	}
	
	@RequestMapping(params = "form")
	public String loginForm() {
		return "login/loginForm";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String login(@Validated(Login.class) UserForm userForm,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "login/loginForm";
		}
		return "forward:/login";
	}

}
