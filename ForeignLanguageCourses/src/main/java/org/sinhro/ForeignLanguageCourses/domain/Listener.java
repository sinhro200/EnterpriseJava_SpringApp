package org.sinhro.ForeignLanguageCourses.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Listener {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String secondName;

    @ManyToOne
    private Group group;

    private Integer beginWeek;

    public String prettyString() {
        return "Listener{" +
            "secondName='" + secondName + '\'' +
            ", group=" + group.prettyString() +
            ", beginWeek=" + beginWeek +
            "}PS";
    }
}
