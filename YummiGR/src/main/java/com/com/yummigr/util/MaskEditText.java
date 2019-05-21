package com.com.yummigr.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MaskEditText {
	
	protected static final String MASK_NUMBER_PATTERN = "/^\\[0-9]{2}\\ [0-9]?[0-9]{4}[0-9]{4}$/";
	protected static final String MASK_EMAIL_PATTERN = "[A-Za-z0-9\\\\._-]+@[A-Za-z]+\\\\.[A-Za-z]+";
	
	public MaskEditText() {
		
	}
	 
	public boolean valid_pattern(String param) {
		 return param.matches("[0-9]+");
	}
	
	
	public boolean valid_pattern_email(String param) {
		if(param.equals("") || param == null) {
			return false;
		}
		return true;
	}
}
