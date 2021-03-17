package org.sinhro.ForeignLanguageCourses.repository;

import org.sinhro.ForeignLanguageCourses.domain.Language;

import java.util.List;

public interface ILanguageRepository {
    List<Language> languages();

    Language getById(Integer id);
}
