package com.erp.blang.model;

public class LoggerModel extends BaseModel{
	
	private String jobName;
	private String message;
	private String exceptionType;
	private Throwable errorStackTrace;
	private Object object;
	private String apiUrl;
	
	private LoggerModel(LoggerBuilder loggerBuilder) {
		super();
		this.jobName = loggerBuilder.jobName;
		this.message = loggerBuilder.message;
		this.exceptionType = loggerBuilder.exceptionType;
		this.errorStackTrace = loggerBuilder.errorStackTrace;
		this.object = loggerBuilder.object;
		this.apiUrl=loggerBuilder.apiUrl;
	}
	
	public String getJobName() {
		return jobName;
	}
	public String getMessage() {
		return message;
	}
	public String getExceptionType() {
		return exceptionType;
	}
	public Throwable getErrorStackTrace() {
		return errorStackTrace;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}
	public void setErrorStackTrace(Throwable errorStackTrace) {
		this.errorStackTrace = errorStackTrace;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	
	public String getPath() {
		return apiUrl;
	}

	public void setPath(String path) {
		this.apiUrl = path;
	}

	

	@Override
	public String toString() {
		return "LoggerModel [jobName=" + jobName + ", message=" + message + ", exceptionType=" + exceptionType
				+ ", errorStackTrace=" + errorStackTrace + ", object=" + object + ", apiUrl=" + apiUrl + "]";
	}

	public static class LoggerBuilder{
		private String jobName;
		private String message;
		private String sellerId;
		private String vAccountId;
		private String marketPlaceName;
		private String exceptionType;
		private Throwable errorStackTrace;
		private Object object;
		private String apiUrl;
		
		public LoggerBuilder(String jobName,  String message) {
			this.jobName = jobName;
			this.message = message;
		}
		
		public LoggerBuilder(String methodName, String path, String message2) {
			this.jobName = methodName;
			this.apiUrl=path;
			this.message = message2;
		}

		/*public LoggerBuilder(String jobName, String message, String sellerId, String marketPlaceName, String vAccountId) {
			this.jobName = jobName;
			this.message = message;
			this.sellerId = sellerId;
			this.marketPlaceName = marketPlaceName;
			this.vAccountId = vAccountId;
		}*/
		 public LoggerBuilder setSellerId(String sellerId) {
	            this.sellerId = sellerId;
	            return this;
	        }
	        public LoggerBuilder setMarketPlaceName(String marketPlaceName) {
	            this.marketPlaceName = marketPlaceName;
	            return this;
	        }
	        public LoggerBuilder setVAccountId(String vAccountId) {
	            this.vAccountId = vAccountId;
	            return this;
	        }
		
		public LoggerBuilder setExceptionType(String exceptionType) {
			this.exceptionType = exceptionType;
			return this;
		}
		
		public LoggerBuilder setErrorStackTrace(Throwable t) {
			this.errorStackTrace = t;
			return this;
		}
		
		public LoggerBuilder setObject(Object o) {
			  if(null!=o)
			this.object = o;
			return this;
		}
		
		public LoggerModel build() {
			LoggerModel loggerModel = new LoggerModel(this);
			return loggerModel;
		}
	}	
}
