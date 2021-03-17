package org.sinhro.ForeignLanguageCourses.service.statistic;

import lombok.Getter;

@Getter
public class Statistic {
    private Integer week = 0;
    private Integer globalProfit = 0;

    private Integer handledRequestsCounter = 0;


    public void nextWeek() {
        week++;
    }

    void profit(Integer profit) {
        globalProfit += profit;
    }

    void newRequestsHandled(Integer cnt){
        handledRequestsCounter+=cnt;
    }
}