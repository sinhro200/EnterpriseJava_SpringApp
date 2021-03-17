package org.sinhro.ForeignLanguageCourses.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Listener {
    private Integer id;

    private String secondName;

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
