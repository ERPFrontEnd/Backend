package com.erp.blang.logging.api.impl;

import com.erp.blang.logging.api.VCLogger;

public class VCLogManager {
	
	public static VCLogger getLogger(Class<?> aClass){
		return VCLoggerImpl.getLogger(aClass);
	}
}
