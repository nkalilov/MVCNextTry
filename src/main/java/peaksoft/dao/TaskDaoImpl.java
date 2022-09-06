//package peaksoft.dao;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import peaksoft.entity.Lesson;
//import peaksoft.entity.Task;
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
//public class TaskDaoImpl implements TaskDao{
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    private final EntityManagerFactory entityManagerFactory;
//
//    @Autowired
//    public TaskDaoImpl(EntityManagerFactory entityManagerFactory) {
//        this.entityManagerFactory = entityManagerFactory;
//    }
//
//    @Override
//    public void saveTask(Long lessonId, Task task) {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        EntityTransaction entityTransaction = entityManager.getTransaction();
//        entityTransaction.begin();
//        Lesson lesson = entityManager.find(Lesson.class,lessonId);
//        task.setLessons(lesson);
//        entityManager.merge(task);
//        entityManager.getTransaction().commit();
//    }
//
//    @Override
//    public void updateTask(Long id,Task task) {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        EntityTransaction entityTransaction = entityManager.getTransaction();
//        entityTransaction.begin();
//        Task task1 = entityManager.find(Task.class,id);
//        task1.setTaskName(task.getTaskName());
//        entityManager.merge(task1);
//        entityManager.getTransaction().commit();
//        entityManager.close();
//    }
//
//    @Override
//    public Task getTaskById(Long id) {
//        return entityManager.find(Task.class,id);
//    }
//
//
//    @Override
//    public List<Task> getAllTasks(Long id) {
//        return entityManager.createQuery("SELECT t FROM Task t where t.lessons.lessonId = :id",Task.class)
//                .setParameter("id",id).getResultList();
//    }
//
//    @Override
//    public void deleteTaskById(Long id) {
//        Task task = entityManager.find(Task.class,id);
//        task.setLessons(null);
//        entityManager.remove(task);
//    }
//}
