package capstone.roomof.DTO.ContentDTO.TTSSoundDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TTSSoundDeleteRequestDTO {

    private String memberId;
    private String roomId;
    private String ttsSoundUrl;
}
