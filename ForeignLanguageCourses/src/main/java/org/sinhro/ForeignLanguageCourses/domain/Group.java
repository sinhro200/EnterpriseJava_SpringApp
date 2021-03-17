package org.sinhro.ForeignLanguageCourses.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Group {
    private Integer id;
    private Course course;

    public String prettyString() {
        return "Group{" +
            "course=" + course.prettyString() +
            "}PS";
    }
}
