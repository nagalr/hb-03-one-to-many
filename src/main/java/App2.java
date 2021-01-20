import com.example.data.HibernateUtil;
import com.example.domain.Course;
import com.example.domain.Instructor;
import org.hibernate.Session;

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

            System.out.println("instructor1: " + instructor);

            Instructor instructor2 =
                    new Instructor("Mark", "Jane", "mark@gmail.com");

            System.out.println("instructor2: " + instructor2);

            Course course1 =
                    new Course();

            System.out.println("course: " + course1);

            course1.setTitle("Math");

            instructor.add(course1);

            // start a transaction
            session.beginTransaction();

            // save both objects in one statement since cascade type is All
            session.save(instructor);

            // save the second object that don't has association
            session.save(instructor2);

            session.save(course1);

            // commit the transaction
            session.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("[ERROR] error while opening the session: " + e);
        }
    }
}

// Taken From: https://is.gd/CAOUGF