package org.sinhro.ForeignLanguageCourses.service.request_generator;

import org.sinhro.ForeignLanguageCourses.domain.Request;
import org.sinhro.ForeignLanguageCourses.repository.IIntensityRepository;
import org.sinhro.ForeignLanguageCourses.repository.ILanguageRepository;
import org.sinhro.ForeignLanguageCourses.repository.ILevelRepository;
import org.sinhro.ForeignLanguageCourses.service.name_generator.INameGenerator;
import org.sinhro.ForeignLanguageCourses.service.statistic.StatisticService;
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
    private IIntensityRepository intensityRepository;

    @Autowired
    private ILanguageRepository languageRepository;

    @Autowired
    private ILevelRepository levelRepository;

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
        Integer currentWeek = statisticService.statistic.getWeek();
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
            listElement.get(intensityRepository.intensities()),
            listElement.get(languageRepository.languages()),
            listElement.get(levelRepository.levels())
        );
    }
}
