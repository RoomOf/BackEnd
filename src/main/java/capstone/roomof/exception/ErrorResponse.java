package capstone.roomof.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {

    private boolean isSuccess;
    private String message;
    private int code;

    public ErrorResponse(ErrorCode errorCode){
        this.isSuccess = errorCode.isSuccess();
        this.message = errorCode.getMessage();
        this.code = errorCode.getCode();
    }

}
