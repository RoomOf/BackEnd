package capstone.roomof.exception.MemberException;

import capstone.roomof.exception.ErrorCode;
import lombok.Getter;

@Getter
public class MemberIdDuplicateException extends RuntimeException{

    private ErrorCode errorCode;
    public MemberIdDuplicateException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}
