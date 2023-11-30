package menu;

import entity.Course;
import entity.Student;
import utility.ApplicationContext;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class TeacherMenu {
    public static void handelTeacherMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Teacher menu");
            System.out.println("Teacher code : ");
            String teacherCode = scanner.nextLine();
            System.out.println("Teacher national code : ");
            String nationalCode = scanner.nextLine();
            if (!ApplicationContext.getTeacherService().signIn(nationalCode, teacherCode)) {
                System.out.println("Your record didnt find in database");
                return;
            }
            Long id =
                    ApplicationContext.getTeacherService().getIdBasedOnNationalCodeAndCodeForTeacher(nationalCode, teacherCode);

            Boolean getScienceCommitteeById =
                    ApplicationContext.getTeacherService().scienceCommittee(id);

            System.out.println("1- View your profile ");
            System.out.println("2- Give marks to students");
            System.out.println("3- Salary ");
            System.out.println("4- quit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println(ApplicationContext.getTeacherService().load(id));
                case 2:
                    Set<Course> courses = ApplicationContext.getTeacherService().teacherCourse(id);
                    System.out.println(courses);
                    System.out.println("Long courseId : ");
                    String courseId = scanner.next();
                    System.out.println(courses);
                    List<Student> students =
                            ApplicationContext.getTeacherService().seeStudentOfCourse(id, Long.valueOf(courseId));
                    System.out.println(students);
                    ApplicationContext.getTeacherService().giveMarkToStudents(id, (Course) courses, scanner.nextInt());
                case 3:
                    System.out.println(ApplicationContext.getTeacherService().salary(id, Boolean.TRUE.equals(getScienceCommitteeById)));
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        }
    }

}
