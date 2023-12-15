package capstone.roomof.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public enum ErrorCode {
    NOT_FOUND(false, "PAGE NOT FOUND", 404),
    INTER_SERVER_ERROR(false, "INTER SERVER ERROR", 500),

    //JWT token expired
    JWT_ACCESSTOKEN_EXPIRED(false, "ACCESSTOKEN EXPIRED", 5001),
    JWT_REFRESHTOKEN_EXPIRED(false, "REFRESHTOKEN EXPIRED", 5002),

    //Register Exception
    ID_DUPLICATED(false, "ID ALREADY EXIST", 4001),
    EMAIL_DUPLICATED(false, "EMAIL ALREADY EXIST", 4002),

    //Login Exception
    ID_NOT_FOUND(false, "NOT EXISITING MEMBER", 4005),
    INVALID_MEMBERPWD(false, "INVALID MEMBER PWD", 4006),

    //Room exception
    ROOMID_NOT_FOUND(false, "INVALID ROOMID ", 4007),
    NOT_ROOM_OWNER(false, "OWNER CAN DELETE ROOM", 4008),
    INVALID_ROOMPWD(false, "EMAIL ALREADY EXIST", 4009),

    IMAGE_UPLOAD_FAIL(false, "FAIL TO UPLOAD IMAGE", 4010),
    VIDEO_UPLOAD_FAIL(false, "FAIL TO UPLOAD VIDEO", 4011),
    SOUND_UPLOAD_FAIL(false, "FAIL TO UPLOAD SOUND", 4012),
    TTS_SOUND_GENERATE_FAIL(false, "FAIL TO GENERATE TTSSOUND", 4013),
    //415는 Unsupported Media Type
    ;

    private boolean isSuccess;
    private String message;
    private int code;

}
