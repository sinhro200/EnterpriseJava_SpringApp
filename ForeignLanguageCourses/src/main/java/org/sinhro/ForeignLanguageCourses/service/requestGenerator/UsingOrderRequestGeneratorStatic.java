package org.sinhro.ForeignLanguageCourses.service.requestGenerator;

import org.sinhro.ForeignLanguageCourses.domain.Request;
import org.sinhro.ForeignLanguageCourses.repository.IntensityRepository;
import org.sinhro.ForeignLanguageCourses.repository.LanguageRepository;
import org.sinhro.ForeignLanguageCourses.repository.LevelRepository;
import org.sinhro.ForeignLanguageCourses.service.nameGenerator.INameGenerator;
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
    private IntensityRepository intensityRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private LevelRepository levelRepository;

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
            listElement.get(intensityRepository.findAll()),
            listElement.get(languageRepository.findAll()),
            listElement.get(levelRepository.findAll())
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
