package org.sinhro.ForeignLanguageCourses.service;

import org.sinhro.ForeignLanguageCourses.domain.Group;
import org.sinhro.ForeignLanguageCourses.domain.Listener;
import org.sinhro.ForeignLanguageCourses.repository.IListenerRepository;
import org.sinhro.ForeignLanguageCourses.service.statistic.StatisticService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListenerService {

    private Logger log = LoggerFactory.getLogger(ListenerService.class);

    @Autowired
    private IListenerRepository listenerRepository;

    @Autowired
    private StatisticService statisticService;

    Listener newListener(String secondName, Group group) {
        Listener l = new Listener(null, secondName, group, statisticService.statistic.getWeek());
        listenerRepository.add(l);
        log.debug("Создан слушатель " + l.prettyString());
        return l;
    }

    List<Listener> listenersInGroup( Group group){
        return listenerRepository.getByGroup(group);
    }
}
