package jp.co.ntt.ojt.app.user;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;


@ControllerAdvice
public class HandlerController {
	
	 @InitBinder
	 public void initBinder(WebDataBinder binder) {

	  // 文字列フィールドが未入力の場合に、空文字ではなく、null をフォームオブジェクトにバインドする
	  binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	 }

}
