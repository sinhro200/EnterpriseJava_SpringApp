package org.sinhro.ForeignLanguageCourses.service;

import org.sinhro.ForeignLanguageCourses.domain.Course;
import org.sinhro.ForeignLanguageCourses.domain.Group;
import org.sinhro.ForeignLanguageCourses.domain.Listener;
import org.sinhro.ForeignLanguageCourses.domain.Request;
import org.sinhro.ForeignLanguageCourses.service.statistic.StatisticService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RequestsHandlerService {

    private Logger log = LoggerFactory.getLogger(RequestsHandlerService.class);

    @Autowired
    private CourseService courseService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private ListenerService listenerService;

    @Autowired
    private StatisticService statisticService;

    @Value("${maxCountListenersInGroup}")
    private Integer maxCountListenersInGroup;

    @Value("${minCountListenersInGroup}")
    private Integer minCountListenersInGroup;

    @Value("${maxCountListenersInCourse}")
    private Integer maxCountListenersOnCourse;

    private List<Request> handledRequests;


    public List<Request> handleRequests(List<Request> requests) {

        //  1.  Раскидать в существующие группы
        //  2.  Из оставшихся сформировать новые группы если кол-во удовлетворяет количеству мест в группе

        handledRequests = new ArrayList<>();

        //1
        log.info("--> Добавим слушателей из заявок в уже существующие группы");
        int cntAddedInExistingGroups = 0;
        for (Request request : requests)
            cntAddedInExistingGroups += addInExistingGroups(request) ? 1 : 0;
        log.info("--< Готово. Добавлено " + cntAddedInExistingGroups + " слушателей.");

        //2
        //остались заявки для которых все существующие группы заняты
        log.info("--> Обработаем заявки не подходящие для уже существующих групп");
        Set<Request> uniqueRequests = findUniqueRequests(requests);
        for (Request uniqReq : uniqueRequests) {
            createGroupForUniqReq(requests, uniqReq);
        }
        log.info("--< Готово");

        return handledRequests;

    }

    private void createGroupForUniqReq(List<Request> requests, Request uniqReq) {
        List<Request> sameRequestList = requests.stream().filter(uniqReq::equals).collect(Collectors.toList());
        log.debug("На очереди " + sameRequestList.size() + " заявок следующего формата : " + uniqReq.prettyString());

        if (sameRequestList.size() >= minCountListenersInGroup) {
            //одинаковых заявок > minCountListenersInGroup
            //можно сформировать группу

            log.info("Накопилось >= " + minCountListenersInGroup + " одинаковых заявок"
                 +"["+ sameRequestList.size() + "]. " + uniqReq.prettyInfoString()
                + ". Пробуем сформировать группу.");

            Course c = courseService.getByLanguageAndIntensityAndLevel(
                uniqReq.getLanguage(),
                uniqReq.getIntensity(),
                uniqReq.getLevel()
            );
            if (c == null) {
                log.info("Курса данного типа ещё нет");
                c = courseService.newCourse(
                    uniqReq.getIntensity(),
                    uniqReq.getLanguage(),
                    uniqReq.getLevel()
                );
                log.info("Создали новый курс, " + c.prettyString());
            }

            Group group = formGroup(c, sameRequestList);
            while (sameRequestList.size() >= minCountListenersInGroup) {
                group = formGroup(c, sameRequestList);
            }
        }
    }

    private Set<Request> findUniqueRequests(List<Request> requests) {
        return new HashSet<>(requests);
    }

    private Boolean addInExistingGroups(Request request) {
        Course course = courseService.getByLanguageAndIntensityAndLevel(
            request.getLanguage(),
            request.getIntensity(),
            request.getLevel()
        );

        if (course != null) {
            for (Group group : groupService.getFreeByCourse(course)) {
                if (groupService.canAddListener(group)) {
                    Listener l = listenerService.newListener(request.getSecondName(), group);

                    handledRequests.add(request);
                    return true;
                }
            }
        }
        return false;
    }


    private Group formGroup(Course c, List<Request> sameReqList) {
        int countListenersOnCourse = courseService.getListeners(c).size();
        if (countListenersOnCourse <= maxCountListenersOnCourse - minCountListenersInGroup) {
            //Можно сформировать ещё группу, для неё место есть
            Integer availableGroupListenersCount = Math.min(
                maxCountListenersOnCourse - countListenersOnCourse, sameReqList.size()
            );

            Group g = groupService.newGroup(c);
            List<Request> requestsToRemove = new ArrayList<>();
            for (int i = 0; i < availableGroupListenersCount && i < maxCountListenersInGroup; i++) {
                Request req = sameReqList.get(i);
                requestsToRemove.add(req);
                Listener l = listenerService.newListener(req.getSecondName(), g);

                handledRequests.add(req);
            }
            sameReqList.removeAll(requestsToRemove);
            log.info("Новая группа сформирована, " + g.prettyString());
            return g;
        } else {
            log.info("Недостаточно места для новой группы. " +
                "На курсе " + c.prettyString() + " уже "
                + countListenersOnCourse + " слушателей.");
        }
        return null;
    }
}
