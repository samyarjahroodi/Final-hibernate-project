import com.github.javafaker.Faker;
import entity.Student;
import entity.Teacher;
import menu.MainMenu;
import org.hibernate.Session;
import services.StudentService;
import services.TeacherService;
import utility.ApplicationContext;
import utility.SessionFactoryProvider;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        //Session session = SessionFactoryProvider.getSessionFactory().openSession();
        MainMenu.menu();
    }
}

