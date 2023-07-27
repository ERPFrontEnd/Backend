package com.erp.blang.utilities;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import com.erp.blang.logging.api.VCLogger;
import com.erp.blang.logging.api.impl.VCLogManager;


@Component
public class FilesUtilty { 
	
	private static VCLogger logger = VCLogManager.getLogger(FilesUtilty.class);

	public File convertInputStreamToFile(InputStream inputStream, String fileName, String location) throws IOException{
		File targetFile = null;
		try {
			targetFile  = new File(location,fileName);
			FileUtils.copyInputStreamToFile(inputStream, targetFile);
		} finally {
			logger.info("convertInputStreamToFile completed");
		}
		return targetFile;
	}
	
	public InputStream convertFileToInputStream(File s3File) throws IOException {
		try {
			return FileUtils.openInputStream(s3File);
		}
		finally {
			logger.info("convertFileToInputStream completed");
		}
	}

	public boolean deleteFile(File localFile) {
		try {
			return FileUtils.deleteQuietly(localFile);
		}
		finally {
			logger.info("deleteFile completed");
		}
	}
	
}
