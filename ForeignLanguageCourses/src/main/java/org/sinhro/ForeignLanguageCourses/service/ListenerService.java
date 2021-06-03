package org.sinhro.ForeignLanguageCourses.service;

import org.sinhro.ForeignLanguageCourses.domain.Group;
import org.sinhro.ForeignLanguageCourses.domain.Listener;
import org.sinhro.ForeignLanguageCourses.repository.ListenerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListenerService {

    private Logger log = LoggerFactory.getLogger(ListenerService.class);

    @Autowired
    private ListenerRepository listenerRepository;

    @Autowired
    private StatisticService statisticService;

    Listener newListener(String secondName, Group group) {
        Listener l = new Listener(null, secondName, group, statisticService.getCurrentWeek());
        listenerRepository.save(l);
        log.debug("Создан слушатель " + l.prettyString());
        return l;
    }

    List<Listener> listenersInGroup(Group group) {
        return listenerRepository.findListenersByGroup(group);
    }

    public void removeFinishedListeners() {
        List<Listener> listeners = listenerRepository.findAll();
        int cnt = 0;
        log.info("Начата проверка прослушавших весь курс пользователей");
        for (Listener listener : listeners) {
            Integer durationWeeksToStudy = listener.getGroup().getCourse().getDurationWeeks();
            Integer beginWeek = listener.getBeginWeek();
            if (beginWeek + durationWeeksToStudy < statisticService.getCurrentWeek()) {
                log.debug("Пользователь " + listener.prettyString() + " слушал курс последнюю неделю.");
                listenerRepository.delete(listener);
                cnt++;
            }
        }
        log.info(cnt + " слушателей закончили обучение");
    }
}
