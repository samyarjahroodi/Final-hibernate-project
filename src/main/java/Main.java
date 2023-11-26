import com.github.javafaker.Faker;
import domain.Course;
import domain.Student;
import domain.Teacher;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.Session;
import services.CourseService;
import services.StudentService;
import services.TeacherService;
import utility.ApplicationContext;
import utility.SessionFactoryProvider;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
       // generateFakeStudents();
       // generateFakeTeachers();
    }
    private static void generateFakeStudents() {
        Faker faker = new Faker();
        StudentService studentService = ApplicationContext.getStudentService();

        for (int i = 0; i < 500; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String studentCode = faker.number().digits(8);

            Student student = new Student(firstName, lastName, studentCode);
            studentService.saveOrUpdate(student);
        }
    }

    private static void generateFakeTeachers() {
        Faker faker = new Faker();
        TeacherService teacherService = ApplicationContext.getTeacherService();

        for (int i = 0; i < 500; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String teacherCode = faker.number().digits(8);
            double salary = faker.number().randomDouble(2, 30000, 80000);

            Teacher teacher = new Teacher(firstName, lastName, teacherCode, salary);
            teacherService.saveOrUpdate(teacher);
        }
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

