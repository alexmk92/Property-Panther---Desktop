package prcse.pp.misc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validation class to check specific fields, each validate option
 * will return either a True of False value
 */
public class Validate {

    /**
     * Checks whether a name contains Alpha characters only, if
     * any numbers are detected return false, else return true
     * @param name the string we wish to validate
     * @return True if no numbers or special chars found, else false
     */
    public Boolean validateName(String name) {
        Boolean isValid = false;

        // Check the string has a length > 0
        if(name.trim().length() > 0) {
            if(name.matches("[a-zA-Z]+")){
                isValid = true;
            }
        }

        return isValid;
    }

    /**
     * Checks whether an email contains Alpha characters numbers and @, if
     * any numbers are detected return false, else return true
     * @param email the string we wish to validate
     * @return True if no numbers or special chars found, else false
     */
    public Boolean validateEmail(String email) {
        Boolean isValid = false;

        // Sets the pattern
        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern p = Pattern.compile(emailPattern);

        // Check the string has a length > 0
        if(email.trim().length() > 0) {
            Matcher m = p.matcher(email);
            isValid = m.matches() ? true : false;
        }

        return isValid;
    }

    /**
     * Validates a phone number to ensure it is in the correct format
     * @param number - the phone number inserted
     * @return True if successful, else false
     */
    public Boolean validateNumber(String number) {
        Boolean isValid = false;

        // Sets the pattern
        String  numPattern = "(\\+[0-9]{2})?[0-9]{11}";
        Pattern p          = Pattern.compile(numPattern);

        // Check the number is valid
        if(number.trim().length() > 10) {
            Matcher m = p.matcher(number);
            isValid = m.matches() ? true : false;
        }

        return isValid;
    }

    /**
     * Validates to check if all numerics
     * @param number - the price inserted
     * @return True if successful, else false
     */
    public Boolean validateCurrency(String number) {
        Boolean isValid = false;

        // Sets the pattern
        String  numPattern = "[0-9]+(\\.[0-9]{0,2})?";
        Pattern p          = Pattern.compile(numPattern);

        // Check the number is valid
        if(number.trim().length() > 2) {
            Matcher m = p.matcher(number);
            isValid = m.matches() ? true : false;
        }

        return isValid;
    }

    /**
     * Validates an address to ensure it is in the correct format
     * @param address - the address string inserted
     * @return True if successful, else false
     */
    public Boolean validateAddress(String address) {
        Boolean isValid = false;

        // Sets the pattern
        String  addrPattern = "[A-Za-z 0-9,]+";
        Pattern p          = Pattern.compile(addrPattern);

        // Check the number is valid
        if(!address.isEmpty()) {
            Matcher m = p.matcher(address);
            isValid = m.matches() ? true : false;
        }

        return isValid;
    }

    /**
     * Validates a postcode to ensure it is in the correct format
     * @param postcode - the address string inserted
     * @return True if successful, else false
     */
    public Boolean validatePostcode(String postcode) {
        Boolean isValid = false;

        // Sets the pattern
        String  postPattern = "(([A-PR-UW-Z]{1}[A-IK-Y]?)([0-9]?[A-HJKS-UW]?[ABEHMNPRVWXY]?|[0-9]?[0-9]?))\\s?([0-9]{1}[ABD-HJLNP-UW-Z]{2})";
        Pattern p          = Pattern.compile(postPattern);

        // Check the number is valid
        if(!postcode.isEmpty()) {
            Matcher m = p.matcher(postcode);
            isValid = m.matches() ? true : false;
        }

        return isValid;
    }
}
