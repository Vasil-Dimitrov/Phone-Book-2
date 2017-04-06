package mainPackage;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
	
public class PhoneNumbers {
	private static final String NUMBER_PATTERN = "^(\\+359|00359|0){1}(87|88|89){1}[2-9]{1}[0-9]{6}";
	private static Pattern pattern = Pattern.compile(NUMBER_PATTERN);
	private static Matcher matcher;
	
	
	/**
	 * Checks the number string and if valid returns it
	 * in a standardized bulgarian format
	 *  @return Returns the input number in a standartized Bulgarian format if valid and NULL if not
	 */
	public static String compile(String phoneNum) {
		phoneNum = formatNum(phoneNum);
		
		if (!validate(phoneNum)) {
			return null;
		}
		
		phoneNum = normalizeNum(phoneNum);
		return phoneNum;
	}
	
	
	/*
	 * Checks the numbers in the LinkedList and returns only the valid ones
	 * in a standardized bulgarian format
	 */
	public static LinkedList<String> compile(LinkedList<String> phoneNum) {
		LinkedList<String> list = new LinkedList<String>();
		String temp;
		for(int i=0; i<phoneNum.size(); i++){
			temp = compile(phoneNum.get(i));
			if(temp!=null)
				list.add(temp);
		}
		return list;
	}
	
	
	/*
	 * Passing the string trough the needed tests to make sure it is a valid Bulgarian number
	 */
	public static boolean validate(String str) {
		if (str == null)
			return false;
		/*
		 * In most cases when the number is wrong it's because 
		 * the person inputting it wrote one more or less number
		 * so this check will make the validating process faster
		 */
		if (str.length() != 10 && str.length() != 13 && str.length() != 14)
			return false;
		matcher = pattern.matcher(str);
		if (!matcher.matches())
			return false;
		return true;
	}
	
	
	/*
	 * Makes sure if the numbers are written with a - or white space they are still valid
	 * For example: 0878-12-345 or 0878 12 345 will be rendered valid due to this
	 */
	public static String formatNum(String phoneNum) {
		phoneNum = phoneNum.replace("-", "");
		phoneNum = phoneNum.replace(" ", "");
		phoneNum = phoneNum.replaceAll("\t", "");
		return phoneNum;
	}
	
	
	/*
	 * Converts a valid number to the standardized bulgarian format
	 */
	public static String normalizeNum(String phoneNum) {
		if(phoneNum==null)
			return null;
		if(phoneNum.length()<=9)
			return null;
		
		int i = 4;
		if(phoneNum.startsWith("00359"))
			i = 5;
		else if(phoneNum.startsWith("0"))
			i = 1;
		return "+359" + phoneNum.substring(i, phoneNum.length());
	}
}
