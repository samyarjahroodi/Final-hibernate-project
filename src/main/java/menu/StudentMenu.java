package menu;

import entity.Course;
import utility.ApplicationContext;

import java.util.List;
import java.util.Scanner;

public class StudentMenu {
    public static void handelStudentMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Student menu");
            System.out.println("Student code : ");
            String studentCode = scanner.next();
            System.out.println("Student national code : ");
            String studentNationalCode = scanner.next();
            boolean signIn =
                    ApplicationContext.getStudentService().signIn(studentNationalCode, studentCode);
            if (!signIn) {
                System.out.println("Your record didnt find in database");
                return;
            }
            Long id =
                    ApplicationContext.getStudentService().getIdBasedOnNationalCodeAndCodeForStudent(studentNationalCode, studentCode);
            System.out.println("1- View your profile ");
            System.out.println("2- See courses");
            System.out.println("3- Get courses based gpa ");
            System.out.println("4- quit");

            switch (scanner.nextInt()) {
                case 1:
                    ApplicationContext.getStudentService().load(id);
                case 2:
                    ApplicationContext.getStudentService().seeCourses(id);
                case 3:
                    System.out.println("All of the courses : ");
                    System.out.println(ApplicationContext.getStudentService().seeCourses(id));
                    System.out.println("Courses that passed : ");
                    System.out.println(ApplicationContext.getStudentService().passesCoursesWithMarks(id));
                    System.out.println("Courses that not passed : ");
                    List<Course> courses = ApplicationContext.getStudentService().notPassesCourses(id);
//                    ApplicationContext.getStudentService().addCourseToStudent(id, courses);
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        }
    }
}
