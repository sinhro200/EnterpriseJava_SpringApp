package org.sinhro.ForeignLanguageCourses.service.requestGenerator;

import org.sinhro.ForeignLanguageCourses.domain.Request;
import org.sinhro.ForeignLanguageCourses.repository.IntensityRepository;
import org.sinhro.ForeignLanguageCourses.repository.LanguageRepository;
import org.sinhro.ForeignLanguageCourses.repository.LevelRepository;
import org.sinhro.ForeignLanguageCourses.service.nameGenerator.INameGenerator;
import org.sinhro.ForeignLanguageCourses.service.StatisticService;
import org.sinhro.ForeignLanguageCourses.tools.random.IListElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsingOrderRequestGeneratorDynamic implements IRequestGenerator {

    private Logger log = LoggerFactory.getLogger(UsingOrderRequestGeneratorDynamic.class);

    @Autowired
    private IntensityRepository intensityRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private LevelRepository levelRepository;

    @Autowired
    @Qualifier("byOrderWithRandomElement")
    private IListElement listElement;

    @Value("${dynamic-generators.minNewRequestCount}")
    private int minNewRequestCount;

    @Value("${dynamic-generators.maxNewRequestCount}")
    private int maxNewRequestCount;

    @Value("${simulationTimeInTwoWeeks}")
    private Integer simulationTimeInTwoWeeks;

    @Autowired
    private StatisticService statisticService;

    @Autowired
    private INameGenerator nameGenerator;

    @Override
    public List<Request> generate() {
        List<Request> requests = new ArrayList<>();
        int newRequestCount = generateCountRequests();
        for (int i = 0; i < newRequestCount; i++)
            requests.add(generateSingle());
        return requests;
    }

    private Integer generateCountRequests() {
        Integer currentWeek = statisticService.getCurrentWeek();
        Integer maxWeek = simulationTimeInTwoWeeks;
        double topBorder = Math.pow(maxWeek, 2);
        double curVal = Math.pow(maxWeek - currentWeek + 1, 2);
        double factor = curVal / topBorder;
        double interval = (maxNewRequestCount - minNewRequestCount) * factor;
        long result = Math.round(interval) + minNewRequestCount;

        log.trace("Top border " + topBorder);
        log.trace("Current value " + curVal);
        log.trace("Factor " + factor);
        log.trace("Interval " + interval);
        log.trace("Result " + result);

        return ((int) result);
    }

    private Request generateSingle() {
        return new Request(
            null,
            nameGenerator.generateSecondName(),
            listElement.get(intensityRepository.findAll()),
            listElement.get(languageRepository.findAll()),
            listElement.get(levelRepository.findAll())
        );
    }
}
