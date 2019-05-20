package com.com.yummigr.validator.core;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;


public class Validators {
	
	@Autowired
    private Messages messages;

    public boolean isEmpty(String str){
        return str == null || str.isEmpty();
    }

    public boolean isEquals(String a, String b){
        if(a != null && b != null){
            return a.equals(b);
        }
        return a == null && b == null;
    }

    public void minLength(Result result, int minLength, String field, String value) {

        if(!isEmpty(value) && value.length() < minLength){
            result.error( messages.get("validator.minlength", messages.get(field), minLength) );
        }

    }

    public void maxLength(Result result, int maxLength, String field, String value) {

        if(!isEmpty(value) && value.length() > maxLength) {
            result.error( messages.get("validator.maxlength", messages.get(field), maxLength) );
        }

    }

    public void identifier(Result result, String field, String value) {

        if(isEmpty(value)){
            required(result, field, value);
        } else if(!value.matches("[a-z0-9]+") || value.length() < 3 || value.length() > 100){
            result.error( messages.get("validator.identifier", messages.get(field) ) );
        }

    }

    public void required(Result result, String field, Object value) {

        if(value == null){
            result.error( messages.get("validator.required", messages.get(field)));
        } else if(value instanceof String && ((String) value).isEmpty()){
            result.error( messages.get("validator.required", messages.get(field)));
        } else if(value instanceof List && ((List) value).isEmpty()){
            result.error( messages.get("validator.required", messages.get(field)));
        }

    }


}
