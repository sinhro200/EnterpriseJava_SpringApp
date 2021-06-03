package org.sinhro.ForeignLanguageCourses.service;

import org.sinhro.ForeignLanguageCourses.repository.IntensityRepository;
import org.sinhro.ForeignLanguageCourses.repository.LanguageRepository;
import org.sinhro.ForeignLanguageCourses.repository.LevelRepository;
import org.sinhro.ForeignLanguageCourses.repository.StatisticRepository;
import org.sinhro.ForeignLanguageCourses.tools.RepositoryInitializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RepositoryInitializerService {

    @Autowired
    IntensityRepository intensityRepository;

    @Autowired
    LanguageRepository languageRepository;

    @Autowired
    LevelRepository levelRepository;

    @Autowired
    StatisticRepository statisticRepository;

    public void initIfNeeded(){
        if (intensityRepository.findAll().isEmpty())
            intensityRepository.saveAll(RepositoryInitializers.initialIntensities());
        if (languageRepository.findAll().isEmpty())
            languageRepository.saveAll(RepositoryInitializers.initialLanguages());
        if (levelRepository.findAll().isEmpty())
            levelRepository.saveAll(RepositoryInitializers.initialLevels());
    }

}
