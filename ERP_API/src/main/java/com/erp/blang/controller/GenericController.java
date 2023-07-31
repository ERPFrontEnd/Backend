package com.erp.blang.controller;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.erp.blang.dto.Columns;
import com.erp.blang.model.message.Messages;
import com.erp.blang.utilities.Utils;




@RestController
public abstract class GenericController {

//	@Autowired public ConfigurationManager configManager;
	private static final String INTERNAL_ERROR = "Internal Server Error";
	private static final String UNAUTHORIZED = "Unauthorized access";
	private static final String UNPROCESSABLE = "Couldn't process the request. Please try again later.";

	@Autowired
	protected Environment environment;

	protected ResponseEntity<Messages> toSucess(int identifier, String msg) {

		Messages message = new Messages.MessageBuilder()
				.identifier(identifier)
				.detailMessage(msg)
				.build();

		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(message);
	}

	protected ResponseEntity<Messages> to500() {

		Messages message = new Messages.MessageBuilder()
				.error(INTERNAL_ERROR)
				.build();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
	}

	protected ResponseEntity<Messages> toFailedDependency() {

		Messages message = new Messages.MessageBuilder()
				.error(UNPROCESSABLE)
				.build();
		return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(message);
	}

	protected ResponseEntity<Messages> to401(String msg) {
		Messages message = new Messages.MessageBuilder()
				.error(msg)
				.build();
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
	}

	protected ResponseEntity<Messages> to400(String msg) {
		Messages message = new Messages.MessageBuilder()
				.error(msg)
				.build();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
	}

	protected ResponseEntity<Messages> to401() {
		Messages message = new Messages.MessageBuilder()
				.error(UNAUTHORIZED)
				.build();
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
	}

	protected ResponseEntity<Messages> to404(String msg) {
		Messages message = new Messages.MessageBuilder()
				.error(msg)
				.build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
	}

	protected ResponseEntity<Messages> to409(String msg) {
		Messages message = new Messages.MessageBuilder()
				.error(msg)
				.build();
		return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
	}

	protected ResponseEntity<Messages> toSucess(boolean status) {
		Messages message = new Messages.MessageBuilder()
				.status(status)
				.build();
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(message);
	}

	protected ResponseEntity<Messages> toSucessForCreate(boolean status) {
		Messages message = new Messages.MessageBuilder()
				.status(status)
				.build();
		return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(message);
	}

	protected ResponseEntity<Messages> toSucessForCreate(Object data) {
		Messages message = new Messages.MessageBuilder()
				.data(data)
				.build();
		return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(message);
	}


	protected ResponseEntity<Object> toSucessCreate(Object data) {
		return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(data);
	}

	protected ResponseEntity<Object> toSucessCreate(String data) {
		return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(data);
	}

	protected ResponseEntity<Object> toError500(Object data) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(data);
	}

	protected ResponseEntity<Object> toError400(Object data) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
	}
	protected ResponseEntity<Object> toError404(Object data) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(data);
	}

	protected ResponseEntity<Object> toError400(String data) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
	}

	protected ResponseEntity<Object> toSuccess(Object object) {
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(object);
	}

	protected ResponseEntity<Messages> toSucess(Object object) {
		Messages message = new Messages.MessageBuilder()
				.data(object)
				.build();
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(message);
	}

	protected void getColumns(Class<?> clazz,List<Columns> columns) {
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			columns.add(new Columns(field.getName(), Utils.spaceBTCamelCase(field.getName()), true,Utils.findDataType(field.getType()),null));
		}
	}

	protected void getColumns(Class<?> clazz,List<Columns> columns,String...ignoreProperties) {
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			if(!isIgnore(field.getName(),ignoreProperties))
				columns.add(new Columns(field.getName(), Utils.spaceBTCamelCase(field.getName()), true,Utils.findDataType(field.getType()),null));
		}
	}

	protected void getColumns(Class<?> clazz,List<Columns> columns,String[] ignoreProperties,String...addProeprties) {
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			if(!isIgnore(field.getName(),ignoreProperties))
				columns.add(new Columns(field.getName(), Utils.spaceBTCamelCase(field.getName()), true,Utils.findDataType(field.getType()),null));
		}
		if(addProeprties != null) {
			for (int i = 0; i < addProeprties.length; i++) {
				columns.add(new Columns(addProeprties[i], Utils.spaceBTCamelCase(addProeprties[i]), true,"string",null));
			}
		}
	}

	private boolean isIgnore(String value,String...ignoreProperties) {
		if(ignoreProperties == null) return false;
		for (String string : ignoreProperties) {
			if(value.equals(string)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets the message for identifier.
	 *
	 * @param clazz the clazz
	 * @return the message for identifier
	 */
	protected String getMessageForIdentifier(Class<?> clazz) {
		return environment.getProperty("identifier").replace("*", clazz.getSimpleName());
	}

	protected void redirectToUI(HttpServletResponse response,String endpoint) {
		try {
			response.sendRedirect(endpoint);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	protected String getLoggedInUserName() {
//
//		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
//				.getPrincipal();
//		return userDetails.getUsername();
//	}

}
