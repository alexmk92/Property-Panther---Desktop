package prcse.pp.model;

import java.io.Serializable;

/**
 * A room is either VACANT ( No-one rents it ) or it is OCCUPIED ( Someone rents it )
 * @author PRCSE
 */
public enum RoomStatus implements Serializable{

    VACANT,
    OCCUPIED;

    @Override
    public String toString() {
        String result = "UNKNOWN";
        switch(this){
            case VACANT:
                result = "VACANT";
                break;
            case OCCUPIED:
                result = "OCCUPIED";
                break;

        }
        return result;
    }
}