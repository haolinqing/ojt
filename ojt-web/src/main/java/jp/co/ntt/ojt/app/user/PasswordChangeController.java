package jp.co.ntt.ojt.app.user;

import javax.inject.Inject;
import javax.validation.groups.Default;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.web.token.transaction.TransactionTokenCheck;
import org.terasoluna.gfw.web.token.transaction.TransactionTokenType;

import jp.co.ntt.ojt.app.user.UserForm.PasswordChange;
import jp.co.ntt.ojt.app.user.helper.UserHelper;
import jp.co.ntt.ojt.domain.model.User;
import jp.co.ntt.ojt.domain.service.user.LoginUserDetails;
import jp.co.ntt.ojt.domain.service.user.UserService;

/**
 *　変更パスワードのController
 */
@Controller
@TransactionTokenCheck
@RequestMapping("password")
public class PasswordChangeController {
	
	/**
	 * ユーザサービス
	 */
	@Inject
	private UserService userService;
	
	/**
	 * ユーザヘルパークラス
	 */
	@Inject
	private UserHelper userHelper;
	
	/**
	 * ユーザフォームの初期処理
	 * 
	 * @return ユーザフォーム
	 */
	@ModelAttribute
	public UserForm setUpForm() {
		UserForm userForm = new UserForm();
		return userForm;
	}
	
	
	
	
	/**
	 * パスワード変更フォーム画面表示
	 * 
	 * @param model モデル
	 * @return 変更フォームビュー
	 */
	@TransactionTokenCheck(type=TransactionTokenType.BEGIN)
	@RequestMapping(value = "change",params = "form", method = RequestMethod.GET)
	public String changeForm(@AuthenticationPrincipal LoginUserDetails userDetails, Model model) {
		//LoginUserDetails userDetails = (LoginUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("user", userDetails.getUser());
		return "password/changeForm";
	}
	
	/**
	 * パスワード変更処理
	 * 
	 * @param userForm ユーザフォーム
	 * @param bindingResult バインド結果
	 * @param model モデル
	 * @param attributes リダイレクト属性
	 * @return パスワード変更完成のリダイレクト
	 */
	@TransactionTokenCheck(type=TransactionTokenType.IN)
	@RequestMapping(value = "change",method = RequestMethod.POST)
	public String change(@Validated({Default.class,PasswordChange.class}) UserForm userForm, BindingResult bindingResult,Model model,RedirectAttributes attributes) {
		User user = userHelper.appleUser(userForm);
		if(bindingResult.hasErrors()) {
			model.addAttribute("user", user);
			return "password/changeForm";
		}
		try {
			userService.initialization(user);
			return "redirect:change?finish";
		}catch(BusinessException e) {
			attributes.addFlashAttribute(e.getResultMessages());
			return "redirect:change?form";
		}
	}
	
	/**
	 * パスワード変更完成画面表示
	 * 
	 * @param model モデル
	 * @return パスワード変更完成ビュー
	 */
	@RequestMapping(value = "change",params = "finish",method = RequestMethod.GET)
	public String changeFinish(Model model) {
		return "password/changeFinish";
	}

}
