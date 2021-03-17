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
public class Language {
    private Integer id;
    private String languageName;

    public String prettyString() {
        return "Language{" +
            "languageName='" + languageName + '\'' +
            "}PS";
    }
}
