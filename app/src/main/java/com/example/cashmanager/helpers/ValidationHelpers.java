package com.example.cashmanager.helpers;

import java.util.regex.Pattern;

/**
 * @author Vladyslav Dzyhovskyi
 * @company UnitedThinkers
 * @since 2022/01/23
 */
public class ValidationHelpers {

	private static Pattern number = Pattern.compile("\\d+(\\.\\d+)?");

	public static boolean validateText(String value) {
		return !value.isEmpty();
	}

	public static boolean validateNumber(String value) {
		return number.matcher(value).matches();
	}

}
