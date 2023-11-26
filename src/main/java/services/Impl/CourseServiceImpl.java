package services.Impl;

import base.service.BaseEntityService;
import base.service.Impl.BaseEntityServiceImpl;
import domain.Course;
import domain.Employee;
import repositories.CourseRepository;
import repositories.EmployeeRepository;
import services.CourseService;
import services.EmployeeService;

public class CourseServiceImpl
        extends BaseEntityServiceImpl<Course,Long, CourseRepository>
        implements CourseService {

    public CourseServiceImpl(CourseRepository baseEntityRepository) {
        super(baseEntityRepository);
    }
}
