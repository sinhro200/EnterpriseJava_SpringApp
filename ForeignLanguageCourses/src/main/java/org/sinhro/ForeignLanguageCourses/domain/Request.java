package org.sinhro.ForeignLanguageCourses.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Request {
    private Integer id;

    private String secondName;

    private Intensity intensity;
    private Language language;
    private Level level;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return intensity.equals(request.intensity) &&
            language.equals(request.language) &&
            level.equals(request.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(intensity, language, level);
    }

    public String prettyString(){
        return "Request{" +
            "secondName='" + secondName + '\'' +
            ", intensity=" + intensity.getIntensityName() +
            ", language=" + language.getLanguageName() +
            ", level=" + level.getLevelName() +
            "}PS";
    }

    public String prettyInfoString(){
        return "Intensity=" + intensity.getIntensityName() +
            ", language=" + language.getLanguageName() +
            ", level=" + level.getLevelName();
    }

}
