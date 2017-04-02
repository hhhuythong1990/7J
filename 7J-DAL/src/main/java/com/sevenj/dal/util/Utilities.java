package com.sevenj.dal.util;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class contains all common functions use for project
 * 
 * 
 */
public class Utilities {

	// support unique id's
	public static Random random = new Random(System.currentTimeMillis());
	private static long latestTime = 0;
	private static int latestRand = 0;
	private static int seqnr = 0;
	// range to use for converting long numbers: digits, lower case letters,
	// upper case letters
	// (except l (lower case L), to not confuse with 1 (one))
	private static String alphanums = "0123456789abcdefghijkmnopqrstuvwxyz";

	// two digits
	private static int dig2 = alphanums.length() * alphanums.length();

	/**
	 * Convert a number to a relatively short representation, using a high
	 * radix, using digits and letters, both lower and upper case (except
	 * capitals "I" and "O").
	 * 
	 * @param number
	 *            the number to convert
	 * @return the resulting string
	 */
	static public String superRadix(long num, String charset) {
		if (num == 0)
			return "0";

		// the actual radix of 60 (the higher the radix, the smaller the
		// resulting representation)
		int radlen = charset.length();

		// generate the digits
		StringBuilder result = new StringBuilder();
		while (num > 0) {
			int mod = (int) (num % radlen); // get the modulo (remainder) of
											// dividing by 60
			result.insert(0, charset.substring(mod, mod + 1));
			num /= radlen; // integer division
		}
		return result.toString();
	}

	/**
	 * get a unique identifier, the result is shorter than the UUID, but still
	 * likely to be unique the id is built up from three random generators, that
	 * have very different seeds: 1- the time in msec since 1/1/2008 2- a number
	 * from a randomizer with its seed set at start up even if two machines
	 * generate the id at exactly the same time and date in milliseconds, the
	 * chance of the numbers be the same is still only 1 out of 10000 the result
	 * is codified using a 36 radix (thus using all numbers and letters)
	 * 
	 * @return
	 */
	synchronized public static String uniqueid() {
		// extension of base time, in milliseconds, representing the days
		// between, roughly, 1/1/1970 and 8/1/2008
		long ext = (38 * 365 + 9 + 180 + 31) * 24; // (the compiler will
													// optimize this to one
													// number)
		ext *= 3600;
		ext *= 1000;

		// the milliseconds since the extended base time
		long current = System.currentTimeMillis();
		long x = current - ext; // this will make the number smaller, for easier
								// debugging
		int rand;
		if (current == latestTime) {
			// this situation happens when multiple id's are generated,
			// typically
			// during conversion from OG. Use a sequence number to make the
			// value unique
			rand = seqnr++;
			// make sure not to collide with the latest random number
			if (rand == latestRand)
				rand = seqnr++;
		} else {
			// create a random number (seeded when the WS started)
			rand = random.nextInt(dig2);
			seqnr = 0;
			latestTime = current;
			latestRand = rand;
		}

		// create the guid by concatenating the two "numbers" using a radix that
		// includes
		// digits, lower and upper case letters
		return superRadix(x, alphanums) + superRadix(rand, alphanums);
	}
	
	static public boolean validatePassword(String password) {
		Pattern p = Pattern.compile("[A-Z]");
		Matcher m = p.matcher(password);
		if (m.find() && hasNumber(password) && hasLowerCase(password)) {
			return true;
		}
		return false;
	}
	


	public static boolean hasNumber(String s) {
		for (int j = 0;j < s.length();j++) {
			if (Character.isDigit(s.charAt(j))) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean hasLowerCase(String s) {
		for (int j = 0;j < s.length();j++) {
			if (Character.isLowerCase(s.charAt(j))) {
				return true;
			}
		}
		return false;
	}
	
	public static Date getCurrentDate(){
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime();
	}
}
