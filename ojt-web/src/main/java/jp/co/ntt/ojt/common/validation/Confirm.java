package jp.co.ntt.ojt.common.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * パスワードは再パスワードと同じを検証するためのアノテーション. 
 */
@Documented
@Constraint(validatedBy= { ConfirmValidator.class})
@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
public @interface Confirm {
	
	String message() default "{jp.co.ntt.ojt.common.validation.Confirm.message}";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
	
	/**
	 * @return フィールド
	 */
	String field();
	
	/**
	 * @return 確認フィールド
	 */
	String confirmField();
	
	@Documented
	@Target({ TYPE, ANNOTATION_TYPE })
	@Retention(RUNTIME)
	public @interface List {
		Confirm[] value();
	}

}
