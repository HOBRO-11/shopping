package com.hobro11.shopping.shopCommand.validate;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ FIELD, PARAMETER })
@Retention(RUNTIME)
@Constraint(validatedBy = { PhoneValidator.class })
public @interface Phone {
	String regexp() default "^010-\\d{3,4}-\\d{4}$";

	String message() default "{Phone.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
