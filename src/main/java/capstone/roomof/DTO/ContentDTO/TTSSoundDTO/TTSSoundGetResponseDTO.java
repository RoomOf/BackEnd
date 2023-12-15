package capstone.roomof.DTO.ContentDTO.TTSSoundDTO;

import capstone.roomof.domain.content.TTSSound;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TTSSoundGetResponseDTO {
    private List<TTSSoundsDTO> ttsSounds;
}
