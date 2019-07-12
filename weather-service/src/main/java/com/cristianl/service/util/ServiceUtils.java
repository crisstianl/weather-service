package com.cristianl.service.util;

public class ServiceUtils {

	public static boolean isNumeric(final String str) {
		return str.matches("-?\\d+(\\.\\d+)?");
	}

	public static boolean isText(final String str) {
		return str.matches("[a-zA-Z]+");
	}

	public static boolean isZipcode(final String str) {
		return str.matches("^[a-zA-Z0-9_|.| ]*$");
	}

}
