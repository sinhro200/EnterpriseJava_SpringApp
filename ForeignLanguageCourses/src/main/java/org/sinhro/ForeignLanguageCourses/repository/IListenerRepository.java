package org.sinhro.ForeignLanguageCourses.repository;

import org.sinhro.ForeignLanguageCourses.domain.Group;
import org.sinhro.ForeignLanguageCourses.domain.Listener;

import java.util.List;


public interface IListenerRepository {
    List<Listener> listeners();
    Listener getById(Integer id);

    void add(Listener l);

    List<Listener> getByGroup(Group group);
}
