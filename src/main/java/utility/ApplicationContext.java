package utility;

import org.hibernate.Session;
import repositories.CourseRepository;
import repositories.EmployeeRepository;
import repositories.Impl.CourseRepositoryImpl;
import repositories.Impl.EmployeeRepositoryImpl;
import repositories.Impl.StudentRepositoryImpl;
import repositories.Impl.TeacherRepositoryImpl;
import repositories.StudentRepository;
import repositories.TeacherRepository;
import services.CourseService;
import services.EmployeeService;
import services.Impl.CourseServiceImpl;
import services.Impl.EmployeeServiceImpl;
import services.Impl.StudentServiceImpl;
import services.Impl.TeacherServiceImpl;
import services.StudentService;
import services.TeacherService;


public class ApplicationContext {

    public ApplicationContext() {

    }

    private static final Session session;

    private static final EmployeeRepository EMPLOYEE_REPOSITORY;

    private static final StudentRepository STUDENT_REPOSITORY;

    private static final TeacherRepository TEACHER_REPOSITORY;

    private static final EmployeeService EMPLOYEE_SERVICE;

    private static final StudentService STUDENT_SERVICE;

    private static final TeacherService TEACHER_SERVICE;

    private static final CourseRepository COURSE_REPOSITORY;

    private static final CourseService COURSE_SERVICE;


    static {
        session = SessionFactoryProvider.getSessionFactory().openSession();
        EMPLOYEE_REPOSITORY = new EmployeeRepositoryImpl(session);
        STUDENT_REPOSITORY = new StudentRepositoryImpl(session);
        TEACHER_REPOSITORY = new TeacherRepositoryImpl(session);
        EMPLOYEE_SERVICE = new EmployeeServiceImpl(EMPLOYEE_REPOSITORY);
        STUDENT_SERVICE = new StudentServiceImpl(STUDENT_REPOSITORY);
        TEACHER_SERVICE = new TeacherServiceImpl(TEACHER_REPOSITORY);
        COURSE_REPOSITORY = new CourseRepositoryImpl(session);
        COURSE_SERVICE = new CourseServiceImpl(COURSE_REPOSITORY);
    }

    public static EmployeeService getEmployeeService() {
        return EMPLOYEE_SERVICE;
    }

    public static StudentService getStudentService() {
        return STUDENT_SERVICE;
    }

    public static TeacherService getTeacherService() {
        return TEACHER_SERVICE;
    }

    public static CourseService getCourseService() {
        return COURSE_SERVICE;
    }


}
