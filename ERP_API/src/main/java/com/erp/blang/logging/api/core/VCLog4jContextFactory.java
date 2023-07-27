package com.erp.blang.logging.api.core;

import org.apache.logging.log4j.core.async.AsyncLoggerContextSelector;
import org.apache.logging.log4j.core.impl.Log4jContextFactory;
import org.apache.logging.log4j.core.util.DefaultShutdownCallbackRegistry;

public class VCLog4jContextFactory extends Log4jContextFactory{
	
	public VCLog4jContextFactory() {
		super(new AsyncLoggerContextSelector(), new DefaultShutdownCallbackRegistry());
	}

}
