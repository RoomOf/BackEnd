package capstone.roomof.DTO.ContentDTO.TextDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TextDeleteRequestDTO {

    private String writerId;
    private Long roomId;
    private Long textId;

}
