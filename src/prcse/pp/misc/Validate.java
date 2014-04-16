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

        // Check the string has a length > 0
        if(email.trim().length() > 0) {
            if(email.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,4}")){
                isValid = true;
            }
        }

        return isValid;
    }
}
