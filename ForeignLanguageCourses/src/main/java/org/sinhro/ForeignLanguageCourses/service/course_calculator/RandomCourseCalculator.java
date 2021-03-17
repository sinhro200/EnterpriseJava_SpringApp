package org.sinhro.ForeignLanguageCourses.service.course_calculator;

import org.sinhro.ForeignLanguageCourses.domain.Intensity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomCourseCalculator implements ICourseCalculator {

    private final Integer minCountLessonsPerWeek = 1;
    private final Integer maxCountLessonsPerWeek = 5;

    private final Integer minDurationWeeks = 1;
    private final Integer maxDurationWeeks = 12; // 3 месяца

    @Autowired
    private Random random;

    @Override
    public Integer calculateCountLessonsPerWeek(Intensity intensity) {
        return random.nextInt(maxCountLessonsPerWeek - minCountLessonsPerWeek)
            + minCountLessonsPerWeek;
    }

    @Override
    public Integer calculateDurationWeeks(Intensity intensity) {
        return random.nextInt(maxDurationWeeks - minDurationWeeks)
            + minDurationWeeks;
    }
}
