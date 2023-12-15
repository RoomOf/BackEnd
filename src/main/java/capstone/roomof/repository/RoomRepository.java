package capstone.roomof.repository;

import capstone.roomof.domain.Member;
import capstone.roomof.domain.Room;
import capstone.roomof.domain.content.Image;
import capstone.roomof.domain.content.Sound;
import capstone.roomof.domain.content.Text;
import capstone.roomof.domain.content.Video;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RoomRepository {

    private final EntityManager em;
    private final VideoRepository videoRepository;
    private final SoundRepository soundRepository;
    private final TextRepository textRepository;

    //생성
    public void save(Room room){
        em.persist(room);
    }

    public void delete(Room room){
        Assert.notNull(room, "Entity must not be null!");

        Room found = em.find(Room.class, room.getRoomId());
        em.remove(found);
    }

    //모든 방 객체
    public List<Room> findAll(){
        return em.createQuery("select m from Room m").getResultList();
    }

    //id로 객체 검색
    public Room findOne(Long roomId){
        return em.find(Room.class, roomId);
    }

    //로그인된 멤버가 만든 방 객체 조회
    public List<Room> findAllByOwner(String memberId){
        return em.createQuery("select m from Room m where m.memberId =: memberId", Room.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

    //이름으로 방 조회
    public List<Room> findAllByName(String name){
        return em.createQuery("select m from Room m where m.name =: name", Room.class)
                .setParameter("name", name)
                .getResultList();
    }

}
