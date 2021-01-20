import com.example.data.HibernateUtil;
import com.example.domain.Course;
import com.example.domain.Instructor;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ronnen on 18-Jan-2021
 */


public class App2 {

    public static void main(String[] args) {

        // try-with-resources to close the session at the end
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            // creates two instructor objects and a course object
            Instructor instructor =
                    new Instructor("John", "Doe", "john@gmail.com");

            Instructor instructor2 =
                    new Instructor("Mark", "Jane", "mark@gmail.com");

            Course course1 =
                    new Course("Math", instructor);

            List<Course> courses = new ArrayList<>();
            courses.add(course1);

            // create association
            instructor.setCourses(courses);


//            // creates one instructorDetail object
//            InstructorDetail instructorDetail =
//                    new InstructorDetail("myYouTube", "Golf");
//
//            // associate two objects
//            instructor.setInstructorDetail(instructorDetail);

            // start a transaction
            session.beginTransaction();

            // save both objects in one statement since cascade type is All
            session.save(instructor);

            // save the second object that don't has association
            session.save(instructor2);

            session.save(course1);

            // commit the transaction
            session.getTransaction().commit();

            // close the session that read a student from the DB
//            session.close();

        } catch (Exception e) {
            System.out.println("[ERROR] error while opening the session: " + e);
        }

//        try (Session session = HibernateUtil.getSessionFactory().openSession()){
//
//            // start a transaction
//            session.beginTransaction();
//
//            Long id = 1L;
//
//            // retrieve instructor by id for deletion
//            Instructor inst = session.get(Instructor.class, id);
//
//            // prints the instructorDetail within instructor
//            InstructorDetail instructorDetail = null;
//            if (inst != null) {
//                instructorDetail = inst.getInstructorDetail();
//            }
//
//            System.out.println(instructorDetail);
//
//            // remove the association between the objects
//            // break bidi link, so we can delete instructorDetail
//            // but keep the Instructor
//            if (instructorDetail != null) {
//                instructorDetail.getInstructor().setInstructorDetail(null);
//            }
//
//            // will also delete the associated object (cascade type All)
//            session.delete(instructorDetail);
//
//
//            // commit the transaction
//            session.getTransaction().commit();
//
////            session.close();
//
//        } catch (Exception e) {
//            System.out.println("[ERROR] error while opening the session: " + e);
//        }
    }
}

// Taken From: https://is.gd/CAOUGF