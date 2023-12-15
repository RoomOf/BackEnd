package capstone.roomof.DTO;


import lombok.*;

@Getter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenInfo {

    //JWT에 대한 인증타입, Bearer사용, 이후 HTTP 헤더에 prefix로 붙여주는 타입.
    private String grantType;
    private String accessToken;
    private String refreshToken;

}
