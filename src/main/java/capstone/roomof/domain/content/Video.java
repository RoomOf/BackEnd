package capstone.roomof.domain.content;


import capstone.roomof.domain.Room;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Video {

    @Id
    private String url;

    @JoinColumn(name = "roomId")
    private Long roomId;
}
