package org.sinhro.ForeignLanguageCourses;

import org.sinhro.ForeignLanguageCourses.domain.Request;
import org.sinhro.ForeignLanguageCourses.repository.RequestRepository;
import org.sinhro.ForeignLanguageCourses.service.ListenerService;
import org.sinhro.ForeignLanguageCourses.service.NewRequestsGeneratorService;
import org.sinhro.ForeignLanguageCourses.service.RequestsHandlerService;
import org.sinhro.ForeignLanguageCourses.service.StatisticService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MainProcess {

    private Logger log = LoggerFactory.getLogger(MainProcess.class);

    @Autowired
    private StatisticService statisticService;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private ListenerService listenerService;

    @Autowired
    private NewRequestsGeneratorService newRequestsGeneratorService;

    @Autowired
    private RequestsHandlerService requestsHandlerService;

    public void weekTick() {
        statisticService.startNewWeek();
        log.info("");
        log.info("_____________________________");
        log.info("Неделя номер " + statisticService.getCurrentWeek());
        log.info("-----------------------------");

        //новые заявки
        newRequestsGeneratorService.generate();

        //Все имеющиеся на текущий момент заявки (новые +  необработанные с прошлого раза)
        List<Request> requests = requestRepository.findAll();

        //Обработаем заявки
        List<Request> handledRequests = requestsHandlerService.handleRequests(requests);
        for (Request hReq : handledRequests) {
            requestRepository.delete(hReq);
        }

        //удаляем уже отучившихся слушателей
        listenerService.removeFinishedListeners();

        //Просчёт оплаты следующего периода обучения
        statisticService.endWeek();
    }

    public void end() {

//        log.info("Общая выручка : " + );
    }


    /**
     *      ###     Общий алгоритм на тик      ###
     *
     *
     *
     * Сгенерировать новые заявки
     * Обработать ВСЕ заявки (в т.ч. с прошлого тика)
     *      Распределим слушателей из заявок в старые группы/создав новые группы(курсы)
     * Собрать выручку за следующий период со всех слушателей
     * Убрать слушателей, которые закончили курс
     *
     */
}
