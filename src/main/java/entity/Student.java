package entity;

import base.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Student extends BaseEntity<Long> {

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false,unique = true)
    private String nationalCode;

    @Column(unique = true, nullable = false)
    private String studentCode;

    @OneToMany(mappedBy = "students", cascade = CascadeType.ALL)
    private Set<student_Course> student_courses;

    public Student(String firstname, String lastname, String studentCode) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.studentCode = studentCode;
    }
}
