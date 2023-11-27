import com.github.javafaker.Faker;
import entity.Student;
import entity.Teacher;
import menu.Menu;
import org.hibernate.Session;
import services.StudentService;
import services.TeacherService;
import utility.ApplicationContext;
import utility.SessionFactoryProvider;

public class Main {
    public static void main(String[] args) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        //Menu.menu();
    }

//    private static void generateFakeCourses() {
//        Faker faker = new Faker();
//        CourseService courseService = ApplicationContext.ge;
//        TeacherService teacherService = ApplicationContext.getTeacherService();
//
//        List<Teacher> teachers = teacherService.getAll();
//
//        for (int i = 0; i < 500; i++) {
//            String courseName = faker.educator().course();
//            String courseCode = faker.bothify("COURSE-??##"); // Adjust the pattern as needed
//            int unit = faker.number().numberBetween(1, 5); // Adjust the unit range as needed
//
//            // Randomly assign a teacher to the course
//            Teacher teacher = teachers.get(faker.number().numberBetween(0, teachers.size() - 1));
//
//            Course course = new Course(courseName, courseCode, unit, teacher, null, null);
//            courseService.saveOrUpdate(course);
//        }
//    }
}

