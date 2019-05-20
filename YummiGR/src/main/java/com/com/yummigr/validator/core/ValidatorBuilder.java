package com.com.yummigr.validator.core;

import java.util.LinkedList;
public class ValidatorBuilder<T> {

	
	private LinkedList<Validator<T>> validators;
	
	
	public ValidatorBuilder() {
		this.validators = new LinkedList<>();
	}
	
	public static <U> ValidatorBuilder<U> create(){
		return new ValidatorBuilder<>();
	}
	
	public ValidatorBuilder<T> apply(Validator<T> validator){
		validators.add(validator);
		
		return this;
	}
	
	public Result validate(T object) {
		Result result = new Result();
		
		for(Validator<T> validator : validators) {
			validator.validate(result, object);
		}
		return result;
	}
	
}
