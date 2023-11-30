package entity;

import base.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@ToString
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


    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<student_Course> student_courses;

    public void setStudent_courses(Set<student_Course> student_courses) {
        this.student_courses = student_courses;
    }

}

