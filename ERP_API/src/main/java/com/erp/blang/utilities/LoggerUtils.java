package com.erp.blang.utilities;

import org.springframework.stereotype.Component;

import com.erp.blang.logging.api.VCLogger;
import com.erp.blang.model.LoggerModel;



@Component
public class LoggerUtils {

	public  void logInfo(VCLogger logger, LoggerModel loggerModel) {
		DateUtils.setBaseData(loggerModel, "Logger");
		logger.info("{0}", loggerModel.toString());
	}
	
	public  void logDebug(VCLogger logger, LoggerModel loggerModel) {
		DateUtils.setBaseData(loggerModel, "Logger");
		logger.debug(new StringBuilder(loggerModel.getMessage()).append("{0}").toString(), loggerModel.toString());
	}
	
	public  void logFatal(VCLogger logger, LoggerModel loggerModel) {
		DateUtils.setBaseData(loggerModel, "Logger");
		logger.fatal(new StringBuilder(loggerModel.getMessage()).append("{0}").toString(), loggerModel.toString());
	}
	
	public  void logError(VCLogger logger, LoggerModel loggerModel) {
		DateUtils.setBaseData(loggerModel, "Logger");
		logger.error(new StringBuilder(loggerModel.getMessage()).append("{0}").toString(),loggerModel.getErrorStackTrace(), loggerModel.toString());
	}
}