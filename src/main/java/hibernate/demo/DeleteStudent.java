package hibernate.demo;

import hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudent {
    public static void main(String args[]) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            int studentId = 5;
            session.beginTransaction();

            System.out.println("Removing Student with ID " + studentId);
            Student student = session.get(Student.class, studentId);
            session.delete(student);

            System.out.println("Removing Student with ID 6");
            session.createQuery("delete from Student where id=6")
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
