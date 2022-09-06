//package peaksoft.dao;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import peaksoft.entity.Course;
//import peaksoft.entity.Lesson;
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
//public class LessonDaoImpl implements LessonDao{
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//
//
//    @Override
//    public void saveLesson(Long courseId, Lesson lesson) {
//        EntityManager entityManager = entityManager.createEntityManager();
//        EntityTransaction entityTransaction = entityManager.getTransaction();
//        entityTransaction.begin();
//        Course course = entityManager.find(Course.class,courseId);
//        lesson.setCourses(course);
//        entityManager.merge(lesson);
//        entityManager.getTransaction().commit();
//        entityManager.close();
//    }
//
//    @Override
//    public void updateLesson(Long id,Lesson lesson) {
//        EntityManager entityManager = entityManager.createEntityManager();
//        EntityTransaction entityTransaction = entityManager.getTransaction();
//        entityTransaction.begin();
//        Lesson lesson1 = entityManager.find(Lesson.class,id);
//        lesson1.setLessonName(lesson.getLessonName());
//        entityManager.merge(lesson1);
//        entityManager.getTransaction().commit();
//        entityManager.close();
//    }
//
//    @Override
//    public Lesson getLessonById(Long id) {
//        return entityManager.find(Lesson.class,id);
//    }
//
//    @Override
//    public void deleteLessonById(Long id) {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        EntityTransaction entityTransaction = entityManager.getTransaction();
//        entityTransaction.begin();
//        Lesson lesson = entityManager.find(Lesson.class, id);
//        System.out.println(lesson);
//        entityManager.remove(lesson);
////        entityManager.createQuery("delete from Lesson c where c.lessonId = :id").setParameter("id", Lesson.class).executeUpdate();
//        entityManager.getTransaction().commit();
//        entityManager.close();
//    }
//
//    @Override
//    public List<Lesson> getAllLessons(Long id) {
//        return entityManager.createQuery("SELECT l FROM Lesson l where l.courses.courseId = :id",Lesson.class)
//                .setParameter("id",id).getResultList();
//    }
//}
