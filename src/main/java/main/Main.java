package main;

import org.hibernate.Session;
import utility.SessionFactoryProvider;

public class Main {
    public static void main(String[] args) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
    }
}
