package org.sinhro.ForeignLanguageCourses.repository.impl;

import org.sinhro.ForeignLanguageCourses.domain.Language;
import org.sinhro.ForeignLanguageCourses.repository.ILanguageRepository;
import org.sinhro.ForeignLanguageCourses.tools.Counter;
import org.sinhro.ForeignLanguageCourses.tools.RepositoryInitializers;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class LanguageRepository implements ILanguageRepository {
    private final List<Language> languages = RepositoryInitializers.languages(this);

    @Override
    public List<Language> languages() {
        return new ArrayList<>(languages);
    }

    @Override
    public Language getById(Integer id) {
        return languages.stream()
            .filter(r -> r.getId().equals(id))
            .findAny()
            .orElse(null);
    }
}
