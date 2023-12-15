package capstone.roomof.exception.ContentException;

import capstone.roomof.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ImageUploadException extends RuntimeException{

    private ErrorCode errorCode;
    public ImageUploadException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }

}
