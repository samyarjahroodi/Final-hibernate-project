package entity;

import base.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Course extends BaseEntity<Long> {

    @Column(nullable = false)
    private String nameOfTheCourse;

    @Column(unique = true, nullable = false)
    private String courseCode;

    @Column(nullable = false)
    private int unit;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

//    @OneToMany
//    private List<Student> students;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private Set<student_Course> student_courses;
}
