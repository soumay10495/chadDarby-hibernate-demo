package hibernate.demo;

import hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudent {
    public static void main(String args[]) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            System.out.println("Creating a new Student object...");
            Student student = new Student("Samuel", "Jackson", "samjack@gmail.com");
            session.beginTransaction();
            System.out.println("Student details : " + student);
            System.out.println("Saving the student...");
            session.save(student);
            session.getTransaction().commit();
            System.out.println("Done");


            System.out.println("\nFetching student details with id " + student.getId());
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            Student theStudent = session.get(Student.class, student.getId());
            session.getTransaction().commit();
            System.out.println("Student details : " + theStudent);
            System.out.println("Done");

        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }
}
