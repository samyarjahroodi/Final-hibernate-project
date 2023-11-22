package main;

import entity.Term;
import org.hibernate.Session;
import utility.SessionFactoryProvider;

public class Main {
    public static void main(String[] args) {
        //Session session = SessionFactoryProvider.getSessionFactory().openSession();
        String a = "399";
        a = a + "1";
        System.out.println(a);

    }
}
