package com.hobro11.command.core.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null || value.trim().equals("")) {
            return false;
		}

		String regex = "^010-\\d{3,4}-\\d{4}$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(value);
		if (!m.matches()) {
            return false;
        }
		return true;
    }
    
}
