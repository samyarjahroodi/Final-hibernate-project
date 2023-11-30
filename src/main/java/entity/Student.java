package entity;

import base.domain.BaseEntity;
import lombok.*;

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
    @ToString.Exclude
    private Set<student_Course> student_courses;

    public Student(String firstname, String lastname, String studentCode) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.studentCode = studentCode;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", nationalCode='" + nationalCode + '\'' +
                ", studentCode='" + studentCode + '\'' +
                '}';
    }
}
