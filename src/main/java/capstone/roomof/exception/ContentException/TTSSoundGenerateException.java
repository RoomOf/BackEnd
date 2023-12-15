package capstone.roomof.exception.ContentException;

import capstone.roomof.exception.ErrorCode;
import lombok.Getter;

import java.io.IOException;

@Getter
public class TTSSoundGenerateException extends IOException {
    private ErrorCode errorCode;
    public TTSSoundGenerateException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}
