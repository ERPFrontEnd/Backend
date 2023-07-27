package com.erp.blang.utilities;

/**
 * @author Silxo
 *
 */

import java.util.Date;
import java.util.StringJoiner;

import javax.xml.datatype.XMLGregorianCalendar;


public class Utils {

	public static String spaceBTCamelCase(String camelValue) {
		String str = null;

		if(camelValue == null) return null;

		String[] arr = camelValue.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])");
		StringJoiner sj = new StringJoiner(" ");

		if(arr != null) {

			for (String string : arr) {
				sj.add(string);
			}

			if(sj.toString().length()>1) {
				str = sj.toString().substring(0, 1).toUpperCase() + sj.toString().substring(1);
			}

		}
		return str;
	}

	public static String findDataType(Class<?> clazz) {
		if(clazz == XMLGregorianCalendar.class || clazz == Date.class) return "date";
		else if(clazz == Boolean.class || clazz == boolean.class) return "boolean";
		else if(clazz == Integer.class || clazz == int.class ||clazz == Double.class || clazz == double.class ||clazz == Long.class || clazz == long.class) return "number";	
		return "string";
	}

	public String xmlReplace(String str) {
		if(str == null) return "";
		return str.replaceAll( "&([^;]+(?!(?:\\w|;)))", "&amp;$1" );
	}

	public String xmlDoubleReplace(Double d) {
		String str = d.toString();
		if(str == null) return "";
		return str.replace(".0", "");
	}
}
