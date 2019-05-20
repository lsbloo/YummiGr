package com.com.yummigr.validator.core;


/**
 * respresentação de um objeto validator;
 * @author osvaldoairon
 *
 * @param <T>
 */
public interface Validator<T> {
	
	public void validate(Result result,  T object);

}
