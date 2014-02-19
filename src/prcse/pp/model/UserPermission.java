package prcse.pp.model;

/**
 * Delegates the correct permission to the user on the system,
 * a user can either be a GUEST (anybody on any device), USER (A tenant
 * who has signed up with an agency) or an ADMIN (Works at an agency).
 */
public enum UserPermission {

    GUEST,
    USER,
    ADMIN;

    @Override
    public String toString() {
        String result = "UNKNOWN";
        switch(this){
            case GUEST:
                result = "GUEST";
            break;
            case USER:
                result = "USER";
            break;
            case ADMIN:
                result = "ADMIN";
            break;
        }
        return result;
    }
}
