package org.sinhro.ForeignLanguageCourses.service.courseCalculator;

import org.sinhro.ForeignLanguageCourses.domain.Intensity;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class SimpleCourseCalculator implements ICourseCalculator{
    @Override
    public Integer calculateCountLessonsPerWeek(Intensity intensity) {
        switch (intensity.getConditionalComplexity()) {
            case 1:
                return 1;
            case 2:
                return 3;
            case 3:
                return 5;
        }
        return 3;
    }

    @Override
    public Integer calculateDurationWeeks(Intensity intensity) {
        switch (intensity.getConditionalComplexity()) {
            case 1:
                return 5;
            case 2:
                return 9;
            case 3:
                return 12;
        }
        return 3;
    }
}
