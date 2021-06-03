package org.sinhro.ForeignLanguageCourses.service.statistic;

import org.sinhro.ForeignLanguageCourses.domain.Language;
import org.sinhro.ForeignLanguageCourses.domain.Listener;
import org.sinhro.ForeignLanguageCourses.repository.ListenerRepository;
import org.sinhro.ForeignLanguageCourses.service.incomeCalculator.IIncomeCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.TreeSet;

@Service
public class StatisticService {
    private Logger log = LoggerFactory.getLogger(StatisticService.class);

    @Autowired
    private IIncomeCalculator incomeCalculator;

    @Autowired
    private ListenerRepository listenerRepository;

    public Statistic statistic = new Statistic();
    public Set<Integer> oldListenersIds = new TreeSet<>();

    /*public void requestHandled(Request request, Listener listener) {
        Integer income = incomeCalculator.calculate(request.getLanguage(), false);
        statistic.profit(income);
        log.debug("Обработана заявка " + request.prettyString());
    }*/

    public void calculateIncomeFromListenersForNextTwoWeeks() {
        Integer globalTwoWeeksIncome = 0;
        for (Listener listener : listenerRepository.findAll()) {
            Language language = listener.getGroup().getCourse().getLanguage();
            globalTwoWeeksIncome += incomeCalculator.calculate(language, false);
        }
        statistic.profit(globalTwoWeeksIncome);
        log.info("Выручка за следующие 2 недели обучения " + globalTwoWeeksIncome);
    }

}
