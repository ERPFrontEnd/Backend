package com.erp.blang.logging.api;

public interface VCLogger {

	public void info(Object logMessage, Object... objects);

	public void info(Object logMessage, Throwable e, Object... objects);

	public void warn(Object logMessage, Object... objects);

	public void warn(Object logMessage, Throwable e, Object... objects);

	public void debug(Object logMessage, Object... objects);

	public void debug(Object logMessage, Throwable e, Object... objects);

	public void error(Object logMessage, Object... objects);

	public void error(Object logMessage, Throwable e, Object... objects);

	public void fatal(Object logMessage, Object... objects);

	public void fatal(Object logMessage, Throwable e, Object... objects);
}
