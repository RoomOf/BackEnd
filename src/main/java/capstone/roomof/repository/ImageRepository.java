package capstone.roomof.repository;


import capstone.roomof.domain.content.Image;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ImageRepository {

    private final EntityManager em;

    public void save(Image image){
        em.persist(image);
    }

    public void delete(Image image){
        Assert.notNull(image, "Entity must not be null!");

        Image found = em.find(Image.class, image.getUrl());
        em.remove(found);
    }
    //사진삭제
    public boolean deleteImage(Image image){
        Assert.notNull(image, "Entity must not be null!");
        Image found = em.find(Image.class, image.getUrl());
        em.remove(found);
        return true;
    }
    public Image findImage(String imageUrl){
        Image found = em.find(Image.class, imageUrl);
        return found;
    }

    public Image findThumbnail(Long roomId){
        return em.createQuery("select m from Image m where (m.ifThumbnail =:ifThumbnail and m.roomId =:roomId)", Image.class)
                .setParameter("ifThumbnail", true)
                .setParameter("roomId", roomId)
                .getSingleResult();
    }

    //전체 사진 조회
    public List<Image> findAllImages(Long roomId){
        return em.createQuery("select m from Image m where m.roomId =: roomId", Image.class)
                .setParameter("roomId", roomId)
                .getResultList();
    }
}
