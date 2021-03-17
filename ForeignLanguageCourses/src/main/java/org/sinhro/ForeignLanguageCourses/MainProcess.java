package org.sinhro.ForeignLanguageCourses;

import org.sinhro.ForeignLanguageCourses.domain.Request;
import org.sinhro.ForeignLanguageCourses.repository.IRequestRepository;
import org.sinhro.ForeignLanguageCourses.service.NewRequestsGeneratorService;
import org.sinhro.ForeignLanguageCourses.service.RequestsHandlerService;
import org.sinhro.ForeignLanguageCourses.service.statistic.StatisticService;
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
    private IRequestRepository requestRepository;

    @Autowired
    private NewRequestsGeneratorService newRequestsGeneratorService;

    @Autowired
    private RequestsHandlerService requestsHandlerService;

    public void twoWeekTick() {
        statisticService.statistic.nextWeek();
        log.info("");
        log.info("_____________________________");
        log.info("Неделя номер " + statisticService.statistic.getWeek());
        log.info("-----------------------------");

        //новые заявки
        newRequestsGeneratorService.generate();

        //Все имеющиеся на текущий момент заявки (новые +  необработанные с прошлого раза)
        List<Request> requests = requestRepository.requests();

        //Обработаем заявки
        List<Request> handledRequests = requestsHandlerService.handleRequests(requests);
        for (Request hReq : handledRequests) {
            requestRepository.remove(hReq);
        }

        //Оплата следующих 2 недель обучения
        statisticService.calculateIncomeFromListenersForNextTwoWeeks();
    }

    public void end() {
        log.info("Общая выручка : " + statisticService.statistic.getGlobalProfit());
    }


    /**
     *      ###     Общий алгоритм на тик      ###
     *
     *
    !* Убрать слушателей, которые закончили курс
     * Сгенерировать новые заявки
     * Обработать ВСЕ заявки (в т.ч. с прошлого тика)
     *      Распределим слушателей из заявок в старые группы/создав новые группы(курсы)
    !*      Предложить слушателям из частных групп перейти в общие группы (с шансом 70% они соглашаются)
    !* Предложить оставшимся необработанным заявкам частные занятия
    !*     С шансом 5 % они соглашаются
    !*     Остальных сохраним в репозитории как заявки и на следующий тик обработаем
     * Собрать выручку за следующие 2 недели со всех слушателей
     *
     *
     */

    /**
     * Вопросы:
     *      1: Приватность занятий как добавить?
     *          - добавив группе поле isPrivate, так как структуры занятия нету (и нету смысла добавлять),
     *              в такой группе будет 1 слушатель(поле isPrivate можно даже сделать вычисляемым)
     *
     *      2: Расписание+Занятия+посещаемость увеличат количество Data классов с 7 до 10,
     *          + усложнят задачу в разы, следует опустить или сделать?
     *
     *      3: {Интенсивность}, {Язык}, {Уровень курсов} на данный момент сделаны как записи в репозитории,
     *         Если необходимо сделать сортировку/взять конкретный курс по конкретной {интенсивности},
     *              В данный момент надо искать в БД по названию
     *          Но в теории можно сделать Enum, а надо?
     *
     *
     */
}
