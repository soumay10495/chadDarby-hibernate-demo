package hibernate.demo;

import hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateStudent {
    public static void main(String args[]) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            int studentId = 1;
            System.out.println("Fetching student details...");
            session.beginTransaction();
            Student student = session.get(Student.class, studentId);
            student.setEmail("oldEmail.com");
            session.getTransaction().commit();
            System.out.println("Done");

            System.out.println("Updating student details...");
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createQuery("update Student set email='latestEmail.com'")
                    .executeUpdate();
            session.getTransaction().commit();
            System.out.println("Done");

        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }
}
