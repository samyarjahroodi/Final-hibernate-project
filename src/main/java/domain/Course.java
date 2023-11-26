package domain;

import base.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

    @ManyToMany
    private List<Student> students;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Mark> marks;
}
