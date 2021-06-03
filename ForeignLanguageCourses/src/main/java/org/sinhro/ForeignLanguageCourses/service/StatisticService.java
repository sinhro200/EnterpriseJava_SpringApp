package org.sinhro.ForeignLanguageCourses.service;

import lombok.Getter;
import org.sinhro.ForeignLanguageCourses.domain.Language;
import org.sinhro.ForeignLanguageCourses.domain.Listener;
import org.sinhro.ForeignLanguageCourses.domain.Statistic;
import org.sinhro.ForeignLanguageCourses.repository.ListenerRepository;
import org.sinhro.ForeignLanguageCourses.repository.StatisticRepository;
import org.sinhro.ForeignLanguageCourses.service.incomeCalculator.IIncomeCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticService {
    private Logger log = LoggerFactory.getLogger(StatisticService.class);

    @Autowired
    private StatisticRepository statisticRepository;

    @Autowired
    private IIncomeCalculator incomeCalculator;

    @Autowired
    private ListenerRepository listenerRepository;

    @Getter
    private Integer currentWeek;

    public void startNewWeek() {
        Statistic stat = statisticRepository.lastStatistic();
        if (stat == null)
            this.currentWeek = 1;
        else
            this.currentWeek = stat.getWeekNumber() + 1;
    }

    public void endWeek() {
        Integer weekIncome = 0;
        List<Listener> listenersComeForACurrentWeek =
            listenerRepository.findListenersByBeginWeek(currentWeek);
        List<Listener> allListeners = listenerRepository.findAll();
        for (Listener listener : allListeners) {
            Language language = listener.getGroup().getCourse().getLanguage();
            weekIncome += incomeCalculator.calculate(language, false);
        }
        log.info("Выручка за текущий период " + weekIncome);
        Statistic newStat = new Statistic(
            null,
            currentWeek,
            weekIncome,
            listenersComeForACurrentWeek.size()
        );
        statisticRepository.save(newStat);
    }

}
