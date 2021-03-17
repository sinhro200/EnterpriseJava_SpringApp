package org.sinhro.ForeignLanguageCourses.service.course_calculator;

import org.sinhro.ForeignLanguageCourses.domain.Intensity;

public interface ICourseCalculator {
    Integer calculateCountLessonsPerWeek(Intensity intensity);

    Integer calculateDurationWeeks(Intensity intensity);
}
