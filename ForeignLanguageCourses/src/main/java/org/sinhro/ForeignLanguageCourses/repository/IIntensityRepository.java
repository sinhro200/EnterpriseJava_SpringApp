package org.sinhro.ForeignLanguageCourses.repository;

import org.sinhro.ForeignLanguageCourses.domain.Group;
import org.sinhro.ForeignLanguageCourses.domain.Intensity;
import org.sinhro.ForeignLanguageCourses.domain.Language;
import org.sinhro.ForeignLanguageCourses.domain.Listener;

import java.util.List;

public interface IIntensityRepository {
    List<Intensity> intensities();

    Intensity getById(Integer id);
}
