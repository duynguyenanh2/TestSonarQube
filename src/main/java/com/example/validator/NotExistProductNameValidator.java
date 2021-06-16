package com.example.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.service.ProductService;

public class NotExistProductNameValidator 
	implements ConstraintValidator<NotExistProductName, String> {
	
	public void initialize(NotExistProductName constrainAnnotation) {
		ConstraintValidator.super.initialize(constrainAnnotation);
	}
	
	@Autowired
	private ProductService service;
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return !service.isExistProductName(value);
	}
	
	

}
