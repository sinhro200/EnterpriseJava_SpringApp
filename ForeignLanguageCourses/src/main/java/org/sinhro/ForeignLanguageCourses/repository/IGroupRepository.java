package org.sinhro.ForeignLanguageCourses.repository;

import org.sinhro.ForeignLanguageCourses.domain.Course;
import org.sinhro.ForeignLanguageCourses.domain.Group;

import java.util.List;

public interface IGroupRepository {
    List<Group> groups();

    Group getById(Integer id);

    List<Group> getByCourse(Course course);

    void add(Group group);
}
