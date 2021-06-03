package org.sinhro.ForeignLanguageCourses.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Intensity intensity;
    @ManyToOne
    private Language language;
    @ManyToOne
    private Level level;

    @OneToMany(mappedBy = "course")
    private List<Group> group;

    private Integer durationWeeks;
    private Integer numberLessonsPerWeek;

    public String prettyString() {
        return "Course{" +
            "intensity=" + intensity.getIntensityName() +
            ", language=" + language.getLanguageName() +
            ", level=" + level.getLevelName() +
            ", durationWeeks=" + durationWeeks +
            ", numberLessonsPerWeek=" + numberLessonsPerWeek +
            "}PS";
    }
}
