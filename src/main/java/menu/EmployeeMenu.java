package menu;

import entity.Course;
import entity.Employee;
import entity.Student;
import entity.Teacher;
import utility.ApplicationContext;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class EmployeeMenu {
    public static void handelEmployeeMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Employee menu");
            System.out.println("Employee code : ");
            String employeeCode = scanner.next();
            System.out.println("National code : ");
            String nationalCode = scanner.next();
            boolean signIn = ApplicationContext.getEmployeeService().signIn(nationalCode, employeeCode);
            if (!signIn) {
                System.out.println("Your record didnt find in database");
                return;
            }
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
                    Long idBasedOnNationalCodeAndCodeForStudent =
                            ApplicationContext.getStudentService().getIdBasedOnNationalCodeAndCodeForStudent(studentNationalCode, studentCode);
                    if (exist) {
                        Student existedStudent = ApplicationContext.getStudentService().getExistedStudent(idBasedOnNationalCodeAndCodeForStudent);
                        System.out.println("Firstname : ");
                        existedStudent.setFirstname(scanner.next());
                        System.out.println("Lastname : ");
                        existedStudent.setLastname(scanner.next());
                        ApplicationContext.getEmployeeService().saveOrUpdateStudent(existedStudent);
                    } else {
                        System.out.println("Add a new student to the database");
                        Student student = new Student();
                        System.out.println("Firstname : ");
                        student.setFirstname(scanner.next());
                        System.out.println("Lastname : ");
                        student.setLastname(scanner.next());
                        System.out.println("Student code : ");
                        student.setStudentCode(scanner.next());
                        System.out.println("National code : ");
                        student.setNationalCode(scanner.next());
                        System.out.println(ApplicationContext.getStudentService().notPassesCourses(idBasedOnNationalCodeAndCodeForStudent));
                        System.out.println("id of course : ");
                        Long idOfCourse = scanner.nextLong();
                        student.setStudent_courses(ApplicationContext.getStudentService().addCourseToStudent(idBasedOnNationalCodeAndCodeForStudent,idOfCourse));
                        return;
                    }
                case 2:
                    System.out.println("Teacher national code : ");
                    String teacherNationalCode = scanner.next();
                    System.out.println("Teacher code : ");
                    String teacherCode = scanner.next();
                    boolean exist1 = ApplicationContext.getStudentService().exist(teacherNationalCode);
                    if (exist1) {
                        Long idBasedOnNationalCodeAndCodeForTeacher =
                                ApplicationContext.getStudentService().getIdBasedOnNationalCodeAndCodeForStudent(teacherNationalCode, teacherCode);
                        Teacher existedTeacher =
                                ApplicationContext.getTeacherService().getExistedTeacher(idBasedOnNationalCodeAndCodeForTeacher);
                        System.out.println("Firstname : ");
                        existedTeacher.setFirstname(scanner.next());
                        System.out.println("Lastname : ");
                        existedTeacher.setLastname(scanner.next());
                        System.out.println("Science Committee : ");
                        existedTeacher.setScienceCommittee(scanner.hasNextBoolean());
                        ApplicationContext.getEmployeeService().saveOrUpdateTeacher(existedTeacher);

                    } else {
                        System.out.println("Add new teacher to database");
                        Teacher teacher = new Teacher();
                        System.out.println("Firstname : ");
                        teacher.setFirstname(scanner.next());
                        System.out.println("Lastname : ");
                        teacher.setLastname(scanner.next());
                        System.out.println("Student code : ");
                        teacher.setTeacherCode(scanner.next());
                        System.out.println("National code : ");
                        teacher.setNationalCode(scanner.next());
                        return;
                    }
                case 3:
                    System.out.println("Employee national code : ");
                    String employeeNationalCode = scanner.next();
                    System.out.println("Employee code : ");
                    String employeeCode1 = scanner.next();
                    boolean exist2 = ApplicationContext.getEmployeeService().exist(employeeNationalCode);
                    if (exist2) {
                        Long idBasedOnNationalCodeAndCodeForEmployee =
                                ApplicationContext.getEmployeeService().getIdBasedOnNationalCodeAndCodeForEmployee(employeeNationalCode, employeeCode1);
                        Employee existedEmployee =
                                ApplicationContext.getEmployeeService().getExistedEmployee(idBasedOnNationalCodeAndCodeForEmployee);
                        System.out.println("Firstname : ");
                        existedEmployee.setFirstname(scanner.next());
                        System.out.println("Lastname : ");
                        existedEmployee.setLastname(scanner.next());
                        ApplicationContext.getEmployeeService().saveOrUpdate(existedEmployee);
                    } else {
                        System.out.println("Add new Employee to database");
                        Employee employee = new Employee();
                        System.out.println("Firstname : ");
                        employee.setFirstname(scanner.next());
                        System.out.println("Lastname : ");
                        employee.setLastname(scanner.next());
                        System.out.println("Student code : ");
                        employee.setEmployeeCode(scanner.next());
                        System.out.println("National code : ");
                        employee.setNationalCode(scanner.next());
                        return;
                    }
            }
        }
    }
}
