package utility;

import domain.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SessionFactoryProvider {
    private final static SessionFactory sessionFactory;

    static {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // by default load hibernate.cfg.xml
                .build();

        sessionFactory = new MetadataSources(registry)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Teacher.class)
                .addAnnotatedClass(Mark.class)
                .buildMetadata()
                .buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
