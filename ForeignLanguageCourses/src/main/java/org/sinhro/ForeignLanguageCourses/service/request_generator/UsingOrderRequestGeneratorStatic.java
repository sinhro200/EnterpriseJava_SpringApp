package org.sinhro.ForeignLanguageCourses.service.request_generator;

import org.sinhro.ForeignLanguageCourses.domain.Request;
import org.sinhro.ForeignLanguageCourses.repository.IIntensityRepository;
import org.sinhro.ForeignLanguageCourses.repository.ILanguageRepository;
import org.sinhro.ForeignLanguageCourses.repository.ILevelRepository;
import org.sinhro.ForeignLanguageCourses.service.name_generator.INameGenerator;
import org.sinhro.ForeignLanguageCourses.tools.random.IListElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@Primary
public class UsingOrderRequestGeneratorStatic implements IRequestGenerator {

    @Autowired
    private IIntensityRepository intensityRepository;

    @Autowired
    private ILanguageRepository languageRepository;

    @Autowired
    private ILevelRepository levelRepository;

    @Autowired
    @Qualifier("byOrderWithRandomElement")
    private IListElement listElement;

    @Autowired
    private Random random;

    @Value("${static-generators.minNewRequestCount}")
    private int minNewRequestCount;

    @Value("${static-generators.maxNewRequestCount}")
    private int maxNewRequestCount;

    @Autowired
    private INameGenerator nameGenerator;


    private Request generateSingle() {
        return new Request(
            null,
            nameGenerator.generateSecondName(),
            listElement.get(intensityRepository.intensities()),
            listElement.get(languageRepository.languages()),
            listElement.get(levelRepository.levels())
        );
    }

    @Override
    public List<Request> generate() {
        List<Request> requests = new ArrayList<>();
        int newRequestCount = random.nextInt(maxNewRequestCount - minNewRequestCount) + minNewRequestCount;
        for (int i = 0; i < newRequestCount; i++)
            requests.add(generateSingle());
        return requests;
    }
}
