package menu;

import container.Container;
import entity.Course;
import entity.Student;
import entity.Teacher;
import org.hibernate.Session;
import utility.ApplicationContext;
import utility.SessionFactoryProvider;

import java.util.List;
import java.util.Scanner;

import static container.Container.*;

public class Menu {
    static ApplicationContext applicationContext;
    static Scanner scanner = new Scanner(System.in);
    static Session session = SessionFactoryProvider.getSessionFactory().openSession();

    public static void menu() {
        do {
            System.out.println("---WELCOME TO UNIVERSITY MANAGEMENT SYSTEM---");
            System.out.println("1-Teacher");
            System.out.println("2-Student");
            System.out.println("3-Employee");
            System.out.println("4-quit");
            switch (scanner.nextInt()) {
                case 1:
                    handelTeacherMenu();
                case 2:
                    handelStudentMenu();
                case 3:
                    handelEmployeeMenu();
            }

        } while (scanner.nextInt() == 4);
    }


    private static void handelTeacherMenu() {
        while (true) {
            System.out.println("Teacher menu");
            System.out.println("Teacher code : ");
            String teacherCode = scanner.nextLine();
            System.out.println("Teacher national code : ");
            String nationalCode = scanner.nextLine();
            if (!ApplicationContext.getTeacherService().signIn(teacherCode, nationalCode)) {
                System.out.println("Your record didnt find in database");
                return;
            }
            Long id =
                    getIdBasedOnNationalCodeAndCodeForTeacher(nationalCode, teacherCode);

            Boolean getScienceCommitteeById = getScienceCommitteeById(id);

            System.out.println("1- View your profile ");
            System.out.println("2- Give marks to students");
            System.out.println("3- Salary ");
            System.out.println("4- quit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println(ApplicationContext.getTeacherService().load(id));

                case 2:
                    ApplicationContext.
                    ApplicationContext.getTeacherService().giveMarkToStudents(id,);

                case 3:
                    System.out.println(ApplicationContext.getTeacherService().salary(id, Boolean.TRUE.equals(getScienceCommitteeById)));
                case 4:
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        }
    }

    private static void handelStudentMenu() {
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
            Long id = getIdBasedOnNationalCodeAndCodeForStudent(studentNationalCode, studentCode);
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
                    System.out.println("Courses that not passed : ");
                    List<Course> courses = ApplicationContext.getStudentService().notPassesCourses(id);
                    ApplicationContext.getStudentService().addCourseToStudent(id,courses );
                case 4:
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        }
    }

    private static void handelEmployeeMenu() {
        while (true) {
            System.out.println("Employee code : ");
            String employeeCode = scanner.nextLine();
            System.out.println("National code : ");
            String nationalCode = scanner.nextLine();
            boolean signIn = ApplicationContext.getEmployeeService().signIn(nationalCode, employeeCode);
            if (!signIn) {
                return;
            }
            Long id = getIdBasedOnNationalCodeAndCodeForEmployee(nationalCode, employeeCode);
            System.out.println("1- Register,delete and edit Student information");
            System.out.println("2- Register,delete and edit Teacher information");
            System.out.println("3- Register,delete and edit Employee information");
            System.out.println("4- Register,delete and edit courses");
            switch (scanner.nextInt()) {
                case 1:
                    System.out.println("Student national code : ");
                    String studentNationalCode = scanner.next();
                    System.out.println("Student code : ");
                    String studentCode = scanner.next();
                    boolean exist = ApplicationContext.getStudentService().exist(studentNationalCode);
                    if (exist) {
                        Long idBasedOnNationalCodeAndCodeForEmployee =
                                getIdBasedOnNationalCodeAndCodeForEmployee(studentNationalCode, studentCode);
                        Student existedStudent = getExistedStudent(idBasedOnNationalCodeAndCodeForEmployee);
                        System.out.println("Firstname : ");
                        existedStudent.setFirstname(scanner.next());
                        System.out.println("Lastname : ");
                        existedStudent.setLastname(scanner.next());
                        ApplicationContext.getEmployeeService().saveOrUpdateStudent(existedStudent);
                    } else {
                        System.out.println("Add new student to database");
                        Student student = new Student();
                        System.out.println("Firstname : ");
                        student.setFirstname(scanner.next());
                        System.out.println("Lastname : ");
                        student.setLastname(scanner.next());
                        System.out.println("Student code : ");
                        student.setStudentCode(scanner.next());
                        System.out.println("National code : ");
                        student.setNationalCode(scanner.next());
                    }
            }


        }
    }

}
