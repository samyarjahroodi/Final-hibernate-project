package menu;

import java.util.Scanner;

@SuppressWarnings("unused")
public class MainMenu {
    static Scanner scanner = new Scanner(System.in);

    public static void menu() {
        while (true) {
            System.out.println("---WELCOME TO UNIVERSITY MANAGEMENT SYSTEM---");
            System.out.println("1-Teacher");
            System.out.println("2-Student");
            System.out.println("3-Employee");
            System.out.println("4-quit");
            switch (scanner.nextInt()) {
                case 1:
                    TeacherMenu.handelTeacherMenu();
                    break;
                case 2:
                    StudentMenu.handelStudentMenu();
                    break;
                case 3:
                    EmployeeMenu.handelEmployeeMenu();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        }
    }
}

