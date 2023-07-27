package com.erp.blang.model.message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Silxo
 *
 */
public class ValidationError implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Error> errors;

	/**
	 * A default constructor that initializes errors list.
	 */
	public ValidationError() {
		errors = new ArrayList<>();
	}

	/**
	 * A default constructor that initializes errors list.
	 *
	 * @param error the error
	 */
	public ValidationError(String error) {
		if(errors == null) {
			errors = new ArrayList<>();
		}
	}
	/**
	 * Gets the errors.
	 *
	 * @return the errors
	 */
	public List<Error> getErrors() {
		return errors;
	}

	/**
	 * Sets the errors.
	 *
	 * @param errors the new errors
	 */
	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}

	/**
	 * Adds an error to the list.
	 *
	 * @param error the error
	 */
	public void addError(Error error) {
		this.errors.add(error);
	}
}