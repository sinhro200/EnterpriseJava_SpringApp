package org.sinhro.ForeignLanguageCourses.service;

import org.sinhro.ForeignLanguageCourses.domain.Course;
import org.sinhro.ForeignLanguageCourses.domain.Group;
import org.sinhro.ForeignLanguageCourses.repository.GroupRepository;
import org.sinhro.ForeignLanguageCourses.repository.ListenerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GroupService {

    private Logger log = LoggerFactory.getLogger(GroupService.class);

    @Value("${maxCountListenersInGroup}")
    private Integer maxGroupSize;
    @Value("${minCountListenersInGroup}")
    private Integer minGroupSize;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ListenerRepository listenerRepository;

    Group newGroup(Course course) {
        Group g = new Group(null, course, Collections.emptyList());
        groupRepository.save(g);

        log.debug("Создали группу " + g.prettyString());
        return g;
    }

    List<Group> getByCourse(Course course) {
        return groupRepository.findAllByCourse(course);
    }

    List<Group> getFreeByCourse(Course course) {
        return groupRepository.findAllByCourse(course)
            .stream()
            .filter(this::canAddListener)
            .collect(Collectors.toList())
            ;
    }

    boolean canAddListener(Group group) {
        return listenerRepository.findListenersByGroup(group).size() < maxGroupSize;
    }
}
