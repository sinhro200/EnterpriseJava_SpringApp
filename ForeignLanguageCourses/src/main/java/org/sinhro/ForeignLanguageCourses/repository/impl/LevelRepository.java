package org.sinhro.ForeignLanguageCourses.repository.impl;

import org.sinhro.ForeignLanguageCourses.domain.Level;
import org.sinhro.ForeignLanguageCourses.repository.ILevelRepository;
import org.sinhro.ForeignLanguageCourses.tools.RepositoryInitializers;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LevelRepository implements ILevelRepository {
    private final List<Level> levels = RepositoryInitializers.levels(this);

    @Override
    public List<Level> levels() {
        return new ArrayList<>(levels);
    }

    @Override
    public Level getById(Integer id) {
        return levels.stream()
            .filter(r -> r.getId().equals(id))
            .findAny()
            .orElse(null);
    }

}
