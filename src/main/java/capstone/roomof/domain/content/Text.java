package capstone.roomof.domain.content;

import capstone.roomof.domain.Room;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Text {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "roomId")
    private Long roomId;

    private String writtenDate;
    private String writerId;
    private String content;
    
}
