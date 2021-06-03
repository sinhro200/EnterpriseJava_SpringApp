package org.sinhro.ForeignLanguageCourses.service.requestGenerator;

import org.sinhro.ForeignLanguageCourses.domain.Intensity;
import org.sinhro.ForeignLanguageCourses.domain.Language;
import org.sinhro.ForeignLanguageCourses.domain.Level;
import org.sinhro.ForeignLanguageCourses.domain.Request;
import org.sinhro.ForeignLanguageCourses.repository.IntensityRepository;
import org.sinhro.ForeignLanguageCourses.repository.LanguageRepository;
import org.sinhro.ForeignLanguageCourses.repository.LevelRepository;
import org.sinhro.ForeignLanguageCourses.service.RepositoryInitializerService;
import org.sinhro.ForeignLanguageCourses.service.nameGenerator.INameGenerator;
import org.sinhro.ForeignLanguageCourses.service.statistic.StatisticService;
import org.sinhro.ForeignLanguageCourses.tools.random.IListElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class UsingNamesRequestGenerator implements IRequestGenerator {

    private Logger log = LoggerFactory.getLogger(UsingOrderRequestGeneratorDynamic.class);

    @Autowired
    private IntensityRepository intensityRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    @Qualifier("randomListElement")
    private IListElement randomListElement;

    @Autowired
    private LevelRepository levelRepository;

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

    @Autowired
    private RepositoryInitializerService repositoryInitializerService;

    private List<Intensity> intensitiesWithRandomCountOfElements;
    private List<Language> languagesWithRandomCountOfElements;
    private List<Level> levelsWithRandomCountOfElements;

    @PostConstruct
    private void init(){
        repositoryInitializerService.initIfNeeded();
        intensitiesWithRandomCountOfElements = elementListWithRandomCountElements(
            intensityRepository.findAll()
        );
        languagesWithRandomCountOfElements = elementListWithRandomCountElements(
            languageRepository.findAll()
        );
        levelsWithRandomCountOfElements = elementListWithRandomCountElements(
            levelRepository.findAll()
        );
    }

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
            randomListElement.get(intensitiesWithRandomCountOfElements),
            randomListElement.get(languagesWithRandomCountOfElements),
            randomListElement.get(levelsWithRandomCountOfElements)
        );
    }
    /*
    Возвращает список с убывающим количеством от размера полученног осписка до 0
    случайных элементов
    Например:
    [1,2,3] -> [2,2,2,1,1,3]
     */
    private <T> List<T> elementListWithRandomCountElements(List<T> elements){
        List<T> res = new ArrayList<T>();
        List<T> elems = elements;
        int cnt = elems.size();
        while(!elems.isEmpty()){
            T randomElement = randomListElement.get(elems);
            elems.remove(randomElement);
            for(int i = 0; i < cnt; i ++){
                res.add(randomElement);
            }
            cnt--;
        }
        return res;
    }
}
