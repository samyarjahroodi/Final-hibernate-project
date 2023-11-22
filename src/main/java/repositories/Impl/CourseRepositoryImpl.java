package repositories.Impl;

import base.repository.Impl.BaseEntityRepositoryImpl;
import entity.Course;
import repositories.CourseRepository;

public class CourseRepositoryImpl
        extends BaseEntityRepositoryImpl<Course,Long>
        implements CourseRepository  {

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
        return null;
    }
}
