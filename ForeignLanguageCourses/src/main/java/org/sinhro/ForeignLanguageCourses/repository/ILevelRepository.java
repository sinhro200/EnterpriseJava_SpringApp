package org.sinhro.ForeignLanguageCourses.repository;

import org.sinhro.ForeignLanguageCourses.domain.Level;

import java.util.List;

public interface ILevelRepository {
    List<Level> levels();

    Level getById(Integer id);
}
