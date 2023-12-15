package capstone.roomof.domain;

import capstone.roomof.domain.content.Image;
import capstone.roomof.domain.content.Sound;
import capstone.roomof.domain.content.Text;
import capstone.roomof.domain.content.Video;
import capstone.roomof.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "roomId")
    private Long roomId;

    @JoinColumn(name = "memberId")
    private String memberId;

    @Column(nullable = false)
    private String pwd;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String birthDate;

    private boolean ifPublic;

    private String deadDate;
    private String relationship;
    private String thumbnail;
    private String background;
    private String ttsApi;


}
