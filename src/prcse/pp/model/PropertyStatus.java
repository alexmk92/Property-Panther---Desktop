package prcse.pp.model;

import java.io.Serializable;

/**
 * A property can either be VACANT ( their are room vacancies ) or FULL ( all rooms full )
 * @author PRCSE
 */
public enum PropertyStatus implements Serializable{

    VACANT,
    FULL;


    @Override
    public String toString() {
        String result = "UNKNOWN";
        switch(this){
            case VACANT:
                result = "VACANT";
                break;
            case FULL:
                result = "FULL";
                break;

        }
        return result;
    }
}

