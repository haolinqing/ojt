package jp.co.ntt.ojt.common.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.ObjectUtils;


/**
 * パスワードは再パスワードと同じを検証する. 
 */
public class ConfirmValidator implements ConstraintValidator<Confirm, Object> {
	
	/**
	 * メッセージ
	 */
	private String message;
	
	/**
	 * フィールド
	 */
	private String field;
	
	/**
	 * 確認フィールド
	 */
	private String confirmField;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initialize(Confirm confirm) {
		field = confirm.field();
		confirmField = confirm.confirmField();
		message = confirm.message();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isValid(Object object, ConstraintValidatorContext context) {
		BeanWrapper beanWrapper = new BeanWrapperImpl(object);
		Object fieldObject = beanWrapper.getPropertyValue(field);
		Object confirmFieldObject = beanWrapper.getPropertyValue(confirmField);
		boolean isEquals = ObjectUtils.nullSafeEquals(fieldObject, confirmFieldObject);
		if(!isEquals) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message).addPropertyNode("password").addConstraintViolation();
			return false;
		}
		return true;
	}

}

