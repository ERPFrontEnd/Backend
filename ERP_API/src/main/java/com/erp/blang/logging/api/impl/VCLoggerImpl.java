package com.erp.blang.logging.api.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.spi.ExtendedLogger;
import org.apache.logging.log4j.spi.ExtendedLoggerWrapper;

import com.erp.blang.logging.api.VCLogger;
import com.erp.blang.logging.api.utils.AppUtils;


public class VCLoggerImpl extends ExtendedLoggerWrapper implements VCLogger {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String FQCN = VCLoggerImpl.class.getName();

	public VCLoggerImpl(final Logger logger) {
		super((ExtendedLogger) logger, logger.getName(), logger.getMessageFactory());
	}

	public static VCLogger getLogger(Class<?> aClass) {
		return getLogger(aClass.getName());
	}

	public static VCLogger getLogger(String name) {
		return new VCLoggerImpl(LogManager.getLogger(name));
	}

	@Override
	public void info(Object logMessage, Object... objects) {
		logIfEnabled(FQCN, Level.INFO, null, AppUtils.convertMessage(logMessage, objects), null);
	}

	@Override
	public void info(Object logMessage, Throwable e, Object... objects) {
		logIfEnabled(FQCN, Level.INFO, null, AppUtils.convertMessage(logMessage, objects), e);
	}

	@Override
	public void warn(Object logMessage, Object... objects) {
		logIfEnabled(FQCN, Level.WARN, null, AppUtils.convertMessage(logMessage, objects), null);

	}

	@Override
	public void warn(Object logMessage, Throwable e, Object... objects) {
		logIfEnabled(FQCN, Level.WARN, null, AppUtils.convertMessage(logMessage, objects), e);

	}

	@Override
	public void debug(Object logMessage, Object... objects) {
		logIfEnabled(FQCN, Level.DEBUG, null, AppUtils.convertMessage(logMessage, objects), null);

	}

	@Override
	public void debug(Object logMessage, Throwable e, Object... objects) {
		logIfEnabled(FQCN, Level.DEBUG, null, AppUtils.convertMessage(logMessage, objects), e);

	}

	@Override
	public void error(Object logMessage, Object... objects) {
		logIfEnabled(FQCN, Level.ERROR, null, AppUtils.convertMessage(logMessage, objects), null);

	}

	@Override
	public void error(Object logMessage, Throwable e, Object... objects) {
		logIfEnabled(FQCN, Level.ERROR, null, AppUtils.convertMessage(logMessage, objects), e);
	}

	@Override
	public void fatal(Object logMessage, Object... objects) {
		logIfEnabled(FQCN, Level.FATAL, null, AppUtils.convertMessage(logMessage, objects), null);

	}

	@Override
	public void fatal(Object logMessage, Throwable e, Object... objects) {
		logIfEnabled(FQCN, Level.FATAL, null, AppUtils.convertMessage(logMessage, objects), e);

	}

}
