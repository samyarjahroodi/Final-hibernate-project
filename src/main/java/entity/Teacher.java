package entity;

import base.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Teacher extends BaseEntity<Long> {

    private String firstname;

    private String lastname;

    @Column(unique = true, nullable = false)
    private String nationalCode;

    @Column(unique = true, nullable = false)
    private String teacherCode;

    private double salary;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<Course> course;

    private Boolean scienceCommittee;

    @OneToMany(mappedBy = "mark", cascade = CascadeType.ALL)
    private List<student_Course> student_courses;

    public Teacher(String firstname, String lastname, String teacherCode, double salary) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.teacherCode = teacherCode;
        this.salary = salary;
    }

}
