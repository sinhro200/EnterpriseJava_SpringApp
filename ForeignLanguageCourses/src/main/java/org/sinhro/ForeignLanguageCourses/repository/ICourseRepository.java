package org.sinhro.ForeignLanguageCourses.repository;

import org.sinhro.ForeignLanguageCourses.domain.Course;
import org.sinhro.ForeignLanguageCourses.domain.Intensity;
import org.sinhro.ForeignLanguageCourses.domain.Language;
import org.sinhro.ForeignLanguageCourses.domain.Level;

import java.util.List;

public interface ICourseRepository {
    List<Course> courses();

    Course getById(Integer id);

    Course getByLanguageAndIntensityAndLevel(
        Language language,
        Intensity intensity,
        Level level
    );

    void add(Course course);
}
