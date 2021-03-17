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
public class Intensity {
    private Integer id;
    private String intensityName;

    private Integer conditionalComplexity;


    public String prettyString() {
        return "Intensity{" +
            "intensityName='" + intensityName + '\'' +
            "}PS";
    }
}
