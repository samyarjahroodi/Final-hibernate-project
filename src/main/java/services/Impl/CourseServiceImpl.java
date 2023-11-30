package services.Impl;

import base.service.Impl.BaseEntityServiceImpl;
import entity.Course;
import org.hibernate.Session;
import repositories.CourseRepository;
import services.CourseService;

public class CourseServiceImpl
        extends BaseEntityServiceImpl<Course,Long, CourseRepository>
        implements CourseService {

    public CourseServiceImpl(CourseRepository baseEntityRepository) {
        super(baseEntityRepository);
    }
}
