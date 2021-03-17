package org.sinhro.ForeignLanguageCourses.repository.impl;

import org.sinhro.ForeignLanguageCourses.domain.Course;
import org.sinhro.ForeignLanguageCourses.domain.Group;
import org.sinhro.ForeignLanguageCourses.repository.IGroupRepository;
import org.sinhro.ForeignLanguageCourses.tools.Counter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GroupRepository implements IGroupRepository {
    private final List<Group> groups = new ArrayList<>();

    @Override
    public List<Group> groups() {
        return new ArrayList<>(groups);
    }

    @Override
    public Group getById(Integer id) {
        return groups.stream()
            .filter(r -> r.getId().equals(id))
            .findAny()
            .orElse(null);
    }

    @Override
    public List<Group> getByCourse(Course course) {
        return groups.stream()
            .filter(g -> g.getCourse().equals(course))
            .collect(Collectors.toList());
    }

    @Override
    public void add(Group group) {
        if (group.getId() == null)
            group.setId(Counter.valueThenInc(group));
        groups.add(group);
    }
}
