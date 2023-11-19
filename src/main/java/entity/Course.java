package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String courseCode;
    @Column(nullable = false)
    private String nameOfTheCourse;
    @ManyToOne
    private Teacher teacher;
    @ManyToMany
    private List<Student> students;
    @OneToMany(mappedBy = "mark", cascade = CascadeType.ALL)
    private List<Mark> marks;
}
