package capstone.roomof.exception.ContentException;

import capstone.roomof.exception.ErrorCode;
import lombok.Getter;

@Getter
public class SoundUploadException extends RuntimeException{

    private ErrorCode errorCode;
    public SoundUploadException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }

}
