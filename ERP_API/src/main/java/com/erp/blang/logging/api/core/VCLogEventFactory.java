package com.erp.blang.logging.api.core;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.impl.LogEventFactory;
import org.apache.logging.log4j.message.Message;

public class VCLogEventFactory implements LogEventFactory{

	@Override
	public LogEvent createEvent(String loggerName, Marker marker, String fqcn, Level level, Message data, List<Property> properties,
			Throwable t) {
		return new VCLog4jLogEvent(loggerName, marker, fqcn, level, data, properties, t);
	}

}
