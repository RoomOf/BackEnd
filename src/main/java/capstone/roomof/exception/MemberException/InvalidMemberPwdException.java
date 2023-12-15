package capstone.roomof.exception.MemberException;

import capstone.roomof.exception.ErrorCode;
import lombok.Getter;

@Getter
public class InvalidMemberPwdException extends RuntimeException{

    private ErrorCode errorCode;
    public InvalidMemberPwdException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }

}
