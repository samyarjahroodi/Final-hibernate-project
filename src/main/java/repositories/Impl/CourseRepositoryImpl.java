package repositories.Impl;

import base.repository.Impl.BaseEntityRepositoryImpl;
import entity.Course;
import org.hibernate.Session;
import repositories.CourseRepository;

public class CourseRepositoryImpl
        extends BaseEntityRepositoryImpl<Course, Long>
        implements CourseRepository {

    public CourseRepositoryImpl(Session session) {
        super(session);
    }

    @Override
    public Course load(Long aLong) {
        return null;
    }

    @Override
    public Course saveOrUpdate(Course course) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public Class<Course> getEntityClass() {
        return Course.class;
    }

    @Override
    public String getCodeName() {
        return null;
    }
}
