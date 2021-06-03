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
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Intensity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String intensityName;

    private Integer conditionalComplexity;

    @OneToMany(mappedBy = "intensity")
    private List<Course> courses;

    public Intensity(String intensityName, Integer conditionalComplexity){
        this.intensityName = intensityName;
        this.conditionalComplexity = conditionalComplexity;
    }


    public String prettyString() {
        return "Intensity{" +
            "intensityName='" + intensityName + '\'' +
            "}PS";
    }
}
