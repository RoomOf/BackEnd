package capstone.roomof.DTO.ContentDTO.TextDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TextSearchRequestDTO {

    private String memberId;
    private Long roomId;
    private boolean ifMine;

}
