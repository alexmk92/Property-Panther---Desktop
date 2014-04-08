package prcse.pp.misc;

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

        int count = 0;
        // Check the string has a length > 0
        if(name.trim().length() > 0) {
            for(char c : name.toCharArray()) {
                if(!Character.isDigit(c)) {
                    count++;
                }
            }

            // Check they are the same
            if(count == name.trim().length()) {
                isValid = true;
            }
        }

        return isValid;
    }
}
