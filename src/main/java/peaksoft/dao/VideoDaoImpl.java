package peaksoft.dao;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import peaksoft.entity.Lesson;
//import peaksoft.entity.Task;
//import peaksoft.entity.Video;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.EntityTransaction;
//import javax.persistence.PersistenceContext;
//import javax.transaction.Transactional;
//import java.util.List;
//
//@Repository
//@Transactional
//public class VideoDaoImpl implements VideoDao{
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    private final EntityManagerFactory entityManagerFactory;
//
//    @Autowired
//    public VideoDaoImpl(EntityManagerFactory entityManagerFactory) {
//        this.entityManagerFactory = entityManagerFactory;
//    }
//
//    @Override
//    public void saveVideo(Long lessonId, Video video) {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        EntityTransaction entityTransaction = entityManager.getTransaction();
//        entityTransaction.begin();
//        Lesson lesson = entityManager.find(Lesson.class,lessonId);
//        video.setLesson(lesson);
//        entityManager.merge(video);
//        entityManager.getTransaction().commit();
//
////        Lesson lesson = entityManager.find(Lesson.class,lessonId);
////        lesson.setVideo(video);
////        video.setLesson(lesson);
////        entityManager.merge(video);
//    }
//    @Override
//    public void updateVideo(Long id,String videoName, String link) {
//       Video video = entityManager.find(Video.class,id);
//       video.setVideoName(videoName);
//       video.setLink(link);
//       entityManager.merge(video);
//    }
//
//    @Override
//    public Video getVideoById(Long id) {
//        return entityManager.find(Video.class,id);
//    }
//
//
//    @Override
//    public List<Video> getAllVideos(Long id) {
//        return entityManager.createQuery("SELECT v FROM Video v where v.lesson.lessonId = :id",Video.class)
//                .setParameter("id",id).getResultList();
//    }
//
//    @Override
//    public void deleteVideoById(Long id) {
//        Video video = entityManager.find(Video.class,id);
//        video.setLesson(null);
//        entityManager.remove(video);
//    }
//}
