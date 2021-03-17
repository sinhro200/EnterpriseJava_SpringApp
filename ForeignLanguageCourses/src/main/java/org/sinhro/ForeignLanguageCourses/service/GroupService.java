package org.sinhro.ForeignLanguageCourses.service;

import org.sinhro.ForeignLanguageCourses.domain.Course;
import org.sinhro.ForeignLanguageCourses.domain.Group;
import org.sinhro.ForeignLanguageCourses.repository.IGroupRepository;
import org.sinhro.ForeignLanguageCourses.repository.IListenerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
    private IGroupRepository groupRepository;

    @Autowired
    private IListenerRepository listenerRepository;

    Group newGroup(Course course) {
        Group g = new Group(null, course);
        groupRepository.add(g);

        log.debug("Создали группу " + g.prettyString());
        return g;
    }

    List<Group> getByCourse(Course course) {
        return groupRepository.getByCourse(course);
    }

    List<Group> getFreeByCourse(Course course) {
        return groupRepository.getByCourse(course)
            .stream()
            .filter(this::canAddListener)
            .collect(Collectors.toList())
            ;
    }

    boolean canAddListener(Group group) {
        return listenerRepository.getByGroup(group).size() < maxGroupSize;
    }
}
