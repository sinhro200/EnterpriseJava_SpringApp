package org.sinhro.ForeignLanguageCourses.repository.impl;

import org.sinhro.ForeignLanguageCourses.domain.Course;
import org.sinhro.ForeignLanguageCourses.domain.Intensity;
import org.sinhro.ForeignLanguageCourses.domain.Language;
import org.sinhro.ForeignLanguageCourses.domain.Level;
import org.sinhro.ForeignLanguageCourses.repository.ICourseRepository;
import org.sinhro.ForeignLanguageCourses.tools.Counter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CourseRepository implements ICourseRepository {
    private final List<Course> courses = new ArrayList<>();

    @Override
    public List<Course> courses() {
        return new ArrayList<>(courses);
    }

    @Override
    public Course getById(Integer id) {
        return courses.stream()
            .filter(r -> r.getId().equals(id))
            .findAny()
            .orElse(null);
    }

    @Override
    public Course getByLanguageAndIntensityAndLevel(
        Language language,
        Intensity intensity,
        Level level
    ) {
        return courses.stream()
            .filter(c -> {
                return c.getLanguage().equals(language) &&
                    c.getIntensity().equals(intensity) && c.getLevel().equals(level);
            })
            .findAny()
            .orElse(null);
    }

    @Override
    public void add(Course course) {
        if (course.getId()==null)
            course.setId(Counter.valueThenInc(this));
        courses.add(course);
    }
}
