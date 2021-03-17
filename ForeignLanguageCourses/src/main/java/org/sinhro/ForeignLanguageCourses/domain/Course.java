package org.sinhro.ForeignLanguageCourses.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Course {
    private Integer id;

    private Intensity intensity;
    private Language language;
    private Level level;

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
