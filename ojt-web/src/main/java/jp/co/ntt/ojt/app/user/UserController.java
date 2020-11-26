package jp.co.ntt.ojt.app.user;

import javax.inject.Inject;
import javax.validation.groups.Default;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.terasoluna.gfw.common.exception.BusinessException;

import jp.co.ntt.ojt.app.user.UserForm.Register;
import jp.co.ntt.ojt.app.user.UserForm.Search;
import jp.co.ntt.ojt.app.user.UserForm.Update;
import jp.co.ntt.ojt.app.user.helper.UserHelper;
import jp.co.ntt.ojt.domain.model.User;
import jp.co.ntt.ojt.domain.service.user.UserService;
/**
 *　ユーザのController
 */
@Controller
@RequestMapping("user")
@SessionAttributes(value = "userForm")
public class UserController {
	
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
	 * ユーザ検索フォームの初期処理
	 * 
	 * @return ユーザフォーム
	 */
	@ModelAttribute(value = "userSearchForm")
	public UserForm setUpSearchForm() {
		UserForm userSearchForm = new UserForm();
		return userSearchForm;
	}	
	
	/**
	 * ユーザフォームの初期処理
	 * 
	 * @return ユーザフォーム
	 */
	@ModelAttribute(value = "userForm")
	public UserForm setUpForm() {
		UserForm userForm = new UserForm();
		return userForm;
	}
	
	/**
	 * 検索フォーム画面表示
	 * 
	 * @return 検索フォームビュー
	 */
	@RequestMapping(value = "search",params = "form",method = RequestMethod.GET)
	public String searchForm() {
		return "user/searchForm";
	}

	/**
	 * 検索処理
	 * 
	 * @param userForm ユーザフォーム
	 * @param bindingResult バインド結果
	 * @param model モデル
	 * @param pageable ページ情報
	 * @return 検索結果画面表示
	 */
	@RequestMapping(value = "search")
	public String search(@ModelAttribute(value = "userSearchForm") @Validated({Default.class,Search.class}) UserForm userSearchForm, BindingResult bindingResult, Model model, @PageableDefault(size = 5)Pageable pageable ) {
		// 入力チェックでエラーがある場合は、検索フォーム画面表示のままとする
		if(bindingResult.hasErrors()) {
			return "user/searchForm";
		}
		
		// サービスでの入力チェックエラーは例外が発生する、検索フォーム画面表示のままとする
		try {
			Page<User> page = userService.findPageByInfo(userHelper.appleUser(userSearchForm),pageable);			
			model.addAttribute("page",page);
			model.addAttribute("userSearchForm", userSearchForm);
			return "user/searchList";
		}catch (BusinessException e) {
			model.addAttribute(e.getResultMessages());
			return "user/searchForm";
		}
	}
	
	/**
	 * 削除フォーム画面表示
	 * 
	 * @param userId ユーザID
	 * @param model モデル
	 * @return 削除フォームビュー
	 */
	@RequestMapping(value = "delete",params = "form",method = RequestMethod.GET)
	public String deleteForm(String userId,Model model) {
		User user = userService.findOneByUserId(userId);
		model.addAttribute("userForm",userHelper.appleUserForm(user));
		return "user/deleteForm";
	}
	
	/**
	 * 削除確認フォーム画面表示
	 * 
	 * @return 削除確認ビュー
	 */
	@RequestMapping(value = "delete",params = "confirm",method = RequestMethod.POST)
	public String deleteConfirm() {
		return "user/deleteConfirm";
	}
	
	/**
	 * 削除フォーム再画面表示
	 * 
	 * @return 削除確認ビュー
	 */
	@RequestMapping(value = "delete",params = "redo",method = RequestMethod.POST)
	public String deleteRedo() {
		return "user/deleteForm";
	}
	
	/**
	 * 削除処理
	 * 
	 * @param userForm ユーザフォーム
	 * @param attributes リダイレクト属性
	 * @return 削除完成のリダイレクト
	 */
	@RequestMapping(value = "delete",method = RequestMethod.POST)
	public String delete(@ModelAttribute(value = "userForm",binding = false) UserForm userForm,RedirectAttributes attributes) {
		try {
			userService.delete(userHelper.appleUser(userForm));
			attributes.addFlashAttribute("userId", userForm.getUserId());
			return "redirect:delete?finish";
		}catch(BusinessException e) {
			attributes.addFlashAttribute(e.getResultMessages());
			//return "redirect:delete?form&userId=" + userForm.getUserId();
			return "user/deleteConfirm";
		}
	}
	
	/**
	 * 削除完成画面表示
	 * 
	 * @param sessionStatus セッション状態
	 * @return 削除完成ビュー
	 */
	@RequestMapping(value = "delete",params = "finish",method = RequestMethod.GET)
	public String deleteFinish(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "user/deleteFinish";
	}
	
	/**
	 * 更新フォーム画面表示
	 * 
	 * @param userId ユーザID
	 * @param model モデル
	 * @return 更新フォームビュー
	 */
	@RequestMapping(value = "update",params = {"form","userId"},method = RequestMethod.GET)
	public String updateForm(String userId,Model model) {
		User user = userService.findOneByUserId(userId);
		model.addAttribute("userForm",userHelper.appleUserForm(user));
		return "user/updateForm";
	}
	
	/**
	 * 更新確認フォーム画面表示
	 * 
	 * @param userForm ユーザフォーム
	 * @param bindingResult バインド結果
	 * @param model モデル
	 * @return 更新確認ビュー
	 */
	@RequestMapping(value = "update",params = "confirm",method = RequestMethod.POST)
	public String updateConfirm(@Validated({Default.class,Update.class}) @ModelAttribute(value = "userForm") UserForm userForm, BindingResult bindingResult,Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("user", userHelper.appleUser(userForm));
			return "user/updateForm";
		}
		model.addAttribute("user", userHelper.appleUser(userForm));
		return "user/updateConfirm";
	}
	
	/**
	 * 更新フォーム再画面表示
	 * 
	 * @return 更新確認ビュー
	 */
	@RequestMapping(value = "update",params = "redo",method = RequestMethod.POST)
	public String updateRedo() {
		return "user/updateForm";
	}
	
	/**
	 * 更新処理
	 * 
	 * @param userForm ユーザフォーム
	 * @param attributes リダイレクト属性
	 * @return 更新完成のリダイレクト
	 */
	@RequestMapping(value = "update",method = RequestMethod.POST)
	public String update(@ModelAttribute(value = "userForm",binding = false) UserForm userForm,RedirectAttributes attributes) {
		User user = userHelper.appleUser(userForm);
		try {
			userService.update(user);
			attributes.addFlashAttribute("userId", user.getUserId());
			return "redirect:update?finish";
		}catch(BusinessException e) {
			attributes.addFlashAttribute(e.getResultMessages());
			//return "redirect:update?form&userId=" + user.getUserId();
			return "user/updateConfirm";
		}
	}
	
	/**
	 * 更新完成画面表示
	 * 
	 * @param sessionStatus セッション状態
	 * @return 更新完成ビュー
	 */
	@RequestMapping(value = "update",params = "finish",method = RequestMethod.GET)
	public String updateFinish(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "user/updateFinish";
	}
	
	/**
	 * 登録フォーム画面表示
	 * 
	 * @param sessionStatus セッション状態
	 * @return 登録フォーム初期化のリダイレクト
	 */
	@RequestMapping(value = "register",params = "form",method = RequestMethod.GET)
	public String registerFormInit(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "redirect:register?form&init=finished";
	}
	
	/**
	 * 登録フォーム初期化
	 * 
	 * @return 登録フォームビュー
	 */
	@RequestMapping(value = "register",params = {"form","init=finished"},method = RequestMethod.GET)
	public String registerForm() {
		return "user/registerForm";
	}
	
	/**
	 * userIdが存在するかどうかを判別
	 * 
	 * @param userId ユーザーId
	 * @return userIdが存在した場合にtrue
	 */
	@RequestMapping(value = "isExsitedOfUserId")
	@ResponseBody
	public boolean isExsitOfUserId(String userId) {
		return userService.isExistOfUserId(userId);
	}
	
	/**
	 * 登録確認フォーム画面表示
	 * 
	 * @param userForm ユーザフォーム
	 * @param bindingResult バインド結果
	 * @param model モデル
	 * @return 登録確認ビュー
	 */
	@RequestMapping(value = "register",params = "confirm",method = RequestMethod.POST)
	public String registerConfirm(@Validated({Default.class,Register.class}) @ModelAttribute(value = "userForm") UserForm userForm, BindingResult bindingResult,Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("userForm", userForm);
			return "user/registerForm";
		}
		model.addAttribute("userForm", userForm);
		return "user/registerConfirm";
	}
	
	/**
	 * 登録フォーム再画面表示
	 * 
	 * @param userForm ユーザフォーム
	 * @param model モデル
	 * @return 登録確認ビュー
	 */
	@RequestMapping(value = "register",params = "redo",method = RequestMethod.POST)
	public String registerRedo(UserForm userForm,Model model) {
		model.addAttribute("userForm", userForm);
		return "user/registerForm";
	}
	
	/**
	 * 登録処理
	 * 
	 * @param userForm ユーザフォーム
	 * @param model モデル
	 * @param attributes リダイレクト属性
	 * @return 登録完成のリダイレクト
	 */
	@RequestMapping(value = "register",method = RequestMethod.POST)
	public String register(@ModelAttribute(value = "userForm", binding = false) UserForm userForm,Model model,RedirectAttributes attributes) {
		try {
			User user = userHelper.appleUser(userForm);
			userService.register(user);
			attributes.addFlashAttribute("userId", user.getUserId());
			return "redirect:register?finish";
		}catch(BusinessException e) {
			model.addAttribute(e.getResultMessages());
			model.addAttribute("userForm", userForm);
			return "user/registerForm";
		}
	}
	
	/**
	 * 登録完成画面表示
	 * 
	 * @param sessionStatus セッション状態
	 * @return 登録完成ビュー
	 */
	@RequestMapping(value = "register",params = "finish",method = RequestMethod.GET)
	public String registerFinish(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "user/registerFinish";
	}
	
}