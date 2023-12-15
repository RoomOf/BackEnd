package capstone.roomof.exception.MemberException;

import capstone.roomof.exception.ErrorCode;
import lombok.Getter;

@Getter
public class NoExistingIdException extends RuntimeException{

    private ErrorCode errorCode;
    public NoExistingIdException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}

