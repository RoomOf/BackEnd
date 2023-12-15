package capstone.roomof.exception.RoomException;

import capstone.roomof.exception.ErrorCode;
import lombok.Getter;

@Getter
public class RoomDeleteException extends RuntimeException{

    private ErrorCode errorCode;
    public RoomDeleteException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }

}

