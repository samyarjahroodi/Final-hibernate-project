package entity;

import base.domain.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Mark extends BaseEntity<Long> {
    @ManyToOne
    private Student students;

    @ManyToOne
    private Course course;

    private int mark;

    @ManyToOne
    private Teacher teacher;

    @ManyToOne
    private Term term;

}
