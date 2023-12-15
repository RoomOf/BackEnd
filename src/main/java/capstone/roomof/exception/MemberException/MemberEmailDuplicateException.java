package capstone.roomof.exception.MemberException;

import capstone.roomof.exception.ErrorCode;
import lombok.Getter;

@Getter
public class MemberEmailDuplicateException extends RuntimeException{

    private ErrorCode errorCode;
    public MemberEmailDuplicateException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}
