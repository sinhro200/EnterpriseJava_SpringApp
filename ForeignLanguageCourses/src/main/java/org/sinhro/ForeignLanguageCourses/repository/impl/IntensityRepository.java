package org.sinhro.ForeignLanguageCourses.repository.impl;

import org.sinhro.ForeignLanguageCourses.domain.Intensity;
import org.sinhro.ForeignLanguageCourses.repository.IIntensityRepository;
import org.sinhro.ForeignLanguageCourses.tools.RepositoryInitializers;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class IntensityRepository implements IIntensityRepository {

    private final List<Intensity> intensities = RepositoryInitializers.intensities(this);

    @Override
    public List<Intensity> intensities() {
        return new ArrayList<>(intensities);
    }

    @Override
    public Intensity getById(Integer id) {
        return intensities.stream()
            .filter(r -> r.getId().equals(id))
            .findAny()
            .orElse(null);
    }
}
