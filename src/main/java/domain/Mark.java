package domain;

import base.domain.BaseEntity;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Mark extends BaseEntity<Long> {

    @ManyToOne(cascade = CascadeType.ALL)
    private Student students;

    @ManyToOne(cascade = CascadeType.ALL)
    private Course course;

    private int mark;

    @ManyToOne(cascade = CascadeType.ALL)
    private Teacher teacher;

    private boolean isPass;


}
