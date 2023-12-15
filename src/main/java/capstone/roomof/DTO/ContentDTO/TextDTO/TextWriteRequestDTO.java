package capstone.roomof.DTO.ContentDTO.TextDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TextWriteRequestDTO {

    private String writerId;
    private Long roomId;
    private String writtenDate;
    private String content;

}
