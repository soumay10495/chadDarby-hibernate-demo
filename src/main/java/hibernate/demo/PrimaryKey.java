package hibernate.demo;

import hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKey {

    public static void main(String args[]) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            System.out.println("Creating 3 student objects...");
            Student stud1 = new Student("Ace", "Deuce", "acedeuce@gmail.com");
            Student stud2 = new Student("Zed", "Ed", "zeded@gmail.com");
            Student stud3 = new Student("Lew", "Pew", "lewpew@gmail.com");

            session.beginTransaction();

            System.out.println("Saving students...");
            session.save(stud1);
            session.save(stud2);
            session.save(stud3);

            session.getTransaction().commit();

            System.out.println("Done...");
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }
}
