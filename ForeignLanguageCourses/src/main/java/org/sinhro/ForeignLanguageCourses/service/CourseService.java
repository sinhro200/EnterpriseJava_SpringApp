package org.sinhro.ForeignLanguageCourses.service;

import org.sinhro.ForeignLanguageCourses.domain.Course;
import org.sinhro.ForeignLanguageCourses.domain.Group;
import org.sinhro.ForeignLanguageCourses.domain.Intensity;
import org.sinhro.ForeignLanguageCourses.domain.Language;
import org.sinhro.ForeignLanguageCourses.domain.Level;
import org.sinhro.ForeignLanguageCourses.domain.Listener;
import org.sinhro.ForeignLanguageCourses.repository.ICourseRepository;
import org.sinhro.ForeignLanguageCourses.repository.IGroupRepository;
import org.sinhro.ForeignLanguageCourses.repository.IListenerRepository;
import org.sinhro.ForeignLanguageCourses.service.course_calculator.ICourseCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CourseService {

    private Logger log = LoggerFactory.getLogger(CourseService.class);

    @Autowired
    private ICourseRepository courseRepo;

    @Autowired
    private IGroupRepository groupRepository;

    @Autowired
    private IListenerRepository listenerRepository;

    @Autowired
    private ICourseCalculator courseCalculator;

    Course getByLanguageAndIntensityAndLevel(
        Language language,
        Intensity intensity,
        Level level
    ) {
        return courseRepo.getByLanguageAndIntensityAndLevel(language, intensity, level);
    }

    Course newCourse(Intensity intensity, Language language, Level level) {
        Course c = new Course(
            null,
            intensity,
            language,
            level,
            courseCalculator.calculateDurationWeeks(intensity),
            courseCalculator.calculateCountLessonsPerWeek(intensity)
        );
        courseRepo.add(c);
        log.debug("Создали курс " + c.prettyString());
        return c;
    }

    List<Listener> getListeners(Course c) {
        List<Listener> listeners = new ArrayList<>();
        for (Group group : groupRepository.getByCourse(c)) {
            listeners.addAll(listenerRepository.getByGroup(group));
        }
        return listeners;
    }


}
