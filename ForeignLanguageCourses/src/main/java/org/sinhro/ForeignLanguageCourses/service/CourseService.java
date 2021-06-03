package org.sinhro.ForeignLanguageCourses.service;

import org.sinhro.ForeignLanguageCourses.domain.Course;
import org.sinhro.ForeignLanguageCourses.domain.Group;
import org.sinhro.ForeignLanguageCourses.domain.Intensity;
import org.sinhro.ForeignLanguageCourses.domain.Language;
import org.sinhro.ForeignLanguageCourses.domain.Level;
import org.sinhro.ForeignLanguageCourses.domain.Listener;
import org.sinhro.ForeignLanguageCourses.repository.CourseRepository;
import org.sinhro.ForeignLanguageCourses.repository.GroupRepository;
import org.sinhro.ForeignLanguageCourses.repository.ListenerRepository;
import org.sinhro.ForeignLanguageCourses.service.courseCalculator.ICourseCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class CourseService {

    private Logger log = LoggerFactory.getLogger(CourseService.class);

    @Autowired
    private CourseRepository courseRepo;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ListenerRepository listenerRepository;

    @Autowired
    private ICourseCalculator courseCalculator;

    Course getByLanguageAndIntensityAndLevel(
        Language language,
        Intensity intensity,
        Level level
    ) {
        List<Course> courses = courseRepo.findCoursesByLanguageAndIntensityAndLevel(language, intensity, level);
        if (courses.isEmpty()) {
            return null;
        }
        return courses.get(0);
    }

    Course newCourse(Intensity intensity, Language language, Level level) {
        Course c = new Course(
            null,
            intensity,
            language,
            level,
            Collections.emptyList(),
            courseCalculator.calculateDurationWeeks(intensity),
            courseCalculator.calculateCountLessonsPerWeek(intensity)
        );
        courseRepo.save(c);
        log.debug("Создали курс " + c.prettyString());
        return c;
    }

    List<Listener> getListeners(Course c) {
        List<Listener> listeners = new ArrayList<>();
        for (Group group : groupRepository.findAllByCourse(c)) {
            listeners.addAll(listenerRepository.findListenersByGroup(group));
        }
        return listeners;
    }


}
