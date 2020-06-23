package hibernate.demo;

import hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudent {
    public static void main(String args[]) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();
            System.out.println("Querying the database...");
            List<Student> studentList = session.createQuery("from Student").getResultList();
            System.out.println("Displaying the result...");
            for (Student student : studentList) {
                System.out.println(student.toString());
            }
            session.getTransaction().commit();
            System.out.println("Done\n\n");

            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            System.out.println("Querying the database...");
            studentList = session.createQuery("from Student s where s.lastName='Pew'")
                    .getResultList();
            System.out.println("Displaying the result...");
            for (Student student : studentList) {
                System.out.println(student.toString());
            }

            System.out.println("\n\nQuerying the database...");
            studentList = session.createQuery("from Student s where s.lastName='Pew'" +
                    " OR s.firstName='Samuel'")
                    .getResultList();
            System.out.println("Displaying the result...");
            for (Student student : studentList) {
                System.out.println(student.toString());
            }

            System.out.println("\n\nQuerying the database...");
            studentList = session.createQuery("from Student s where s.email " +
                    "LIKE '%gmail.com'").getResultList();
            System.out.println("Displaying the result...");
            for (Student student : studentList) {
                System.out.println(student.toString());
            }

            session.getTransaction().commit();
            System.out.println("Done");
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }
}
