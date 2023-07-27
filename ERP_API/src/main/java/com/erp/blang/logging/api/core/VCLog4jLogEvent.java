package com.erp.blang.logging.api.core;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.impl.Log4jLogEvent;
import org.apache.logging.log4j.message.Message;

public class VCLog4jLogEvent extends Log4jLogEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VCLog4jLogEvent(String loggerName, Marker marker, String fqcn, Level level, Message data,
			List<Property> properties, Throwable t) {
		super(loggerName, marker, fqcn, level, data, properties, t);
	}
}
