package org.sinhro.ForeignLanguageCourses.repository.impl;

import org.sinhro.ForeignLanguageCourses.domain.Group;
import org.sinhro.ForeignLanguageCourses.domain.Listener;
import org.sinhro.ForeignLanguageCourses.repository.IListenerRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ListenerRepository implements IListenerRepository {
    private final List<Listener> listeners = new ArrayList<>();

    @Override
    public List<Listener> listeners() {
        return new ArrayList<>(listeners);
    }

    @Override
    public Listener getById(Integer id) {
        return listeners.stream()
            .filter(l -> l.getId().equals(id))
            .findAny()
            .orElse(null);
    }

    @Override
    public void add(Listener l) {
        listeners.add(l);
    }

    @Override
    public List<Listener> getByGroup(Group group) {
        return listeners.stream()
            .filter(l -> l.getGroup().equals(group))
            .collect(Collectors.toList());
    }
}
