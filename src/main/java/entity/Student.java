package entity;

import base.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;

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

    @Column(unique = true, nullable = false)
    private String studentCode;

    @ManyToMany
    private List<Course> courses;

    @OneToMany(mappedBy = "mark", cascade = CascadeType.ALL)
    private List<Mark> marks;

}
