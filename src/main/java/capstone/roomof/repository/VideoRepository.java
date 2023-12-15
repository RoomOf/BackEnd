package capstone.roomof.repository;

import capstone.roomof.domain.Member;
import capstone.roomof.domain.Room;
import capstone.roomof.domain.content.Video;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class VideoRepository {

    private final EntityManager em;

    public void save(Video video){
        em.persist(video);
    }

    public void delete(Video video){
        Assert.notNull(video, "Entity must not be null!");

        Video found = em.find(Video.class, video.getUrl());
        em.remove(found);
    }

    //영상 조회
    public Video findVideo(String videoUrl){
        Video found = em.find(Video.class, videoUrl);
        return found;
    }

    //영상삭제
    public boolean deleteVideo(Video video){
        Assert.notNull(video, "Entity must not be null!");
        Video found = em.find(Video.class, video.getUrl());
        em.remove(found);
        return true;
    }

    //전체 영상 조회
    public List<Video> findAllVideos(Long roomId){
        return em.createQuery("select m from Video m where m.roomId =: roomId", Video.class)
                .setParameter("roomId", roomId)
                .getResultList();
    }

}
