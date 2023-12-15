package capstone.roomof.DTO.ContentDTO.TTSSoundDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TTSSoundGenerateRequestDTO {

    private String memberId;
    private Long roomId;
    private String text;

}
