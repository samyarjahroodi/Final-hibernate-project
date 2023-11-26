package menu;

import container.Container;
import utility.ApplicationContext;

import java.util.Scanner;

import static container.Container.*;

public class Menu {
    static ApplicationContext applicationContext;
    static Scanner scanner = new Scanner(System.in);

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


    public static void handelTeacherMenu() {
        while (true) {
            System.out.println("Teacher menu");
            System.out.println("Teacher code : ");
            String teacherCode = scanner.nextLine();
            System.out.println("Teacher national code : ");
            String nationalCode = scanner.nextLine();
            if (!ApplicationContext.getTeacherService().signIn(teacherCode, nationalCode)) {
                System.out.println("Your record didnt find in database");
                return;
            } else {
                continue;
            }
            Long id =
                    getIdBasedOnNationalCodeAndCodeForTeacher(nationalCode, teacherCode);

            Boolean getScienceCommitteeById = getScienceCommitteeById(id);
            System.out.println("1- View your profile ");
            System.out.println("2- Record student's marks ");
            System.out.println("3- Salary ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println(ApplicationContext.getTeacherService().load(id));

                case 2:
                    ApplicationContext.getTeacherService().giveMarkToStudents();

                case 3:
                    System.out.println(ApplicationContext.getTeacherService().salary(id, Boolean.TRUE.equals(getScienceCommitteeById)));
            }
        }
    }

    public static void handelStudentMenu() {
        System.out.println("Student menu");
        System.out.println("Student code : ");
        String studentCode = scanner.next();
        System.out.println("Student national code : ");
        String studentNationalCode = scanner.next();
        ApplicationContext.getStudentService().signIn(studentNationalCode, studentCode);
        System.out.println("1- View your profile ");
        System.out.println("");

    }

    public static void handelEmployeeMenu() {

    }

}
