package org.sinhro.ForeignLanguageCourses.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Level {
    private Integer id;
    private String levelName;

    public String prettyString() {
        return "Level{" +
            "levelName='" + levelName + '\'' +
            "}PS";
    }
}
