package commonfunctions;

public class getMeMyNumber {
	
	 public static String getMeMyNumber(String number, String countryCode)
	    {    
	         String out = number.replaceAll("[^0-9\\+]", "")        //remove all the non numbers (brackets dashes spaces etc.) except the + signs
	                        .replaceAll("(^[1-9].+)", countryCode+"$1")         //if the number is starting with no zero and +, its a local number. prepend cc
	                        .replaceAll("(.)(\\++)(.)", "$1$3")         //if there are left out +'s in the middle by mistake, remove them
	                        .replaceAll("(^0{2}|^\\+)(.+)", "$2")       //make 00XXX... numbers and +XXXXX.. numbers into XXXX...
	                        .replaceAll("^0([1-9])", countryCode+"$1");         //make 0XXXXXXX numbers into CCXXXXXXXX numbers
	         return out;

	    }

}
