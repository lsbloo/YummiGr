package com.com.yummigr.validator.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class Messages {
	
	    @Autowired
	    private MessageSource messageSource;

	    public String get(String key, Object ... parameters) {
	        return messageSource.getMessage(key.trim(), parameters, LocaleContextHolder.getLocale());
	    }

}
