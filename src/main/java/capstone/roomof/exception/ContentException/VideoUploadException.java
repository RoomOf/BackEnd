package capstone.roomof.exception.ContentException;

import capstone.roomof.exception.ErrorCode;
import lombok.Getter;

@Getter
public class VideoUploadException extends RuntimeException{
    private ErrorCode errorCode;
    public VideoUploadException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }

}
