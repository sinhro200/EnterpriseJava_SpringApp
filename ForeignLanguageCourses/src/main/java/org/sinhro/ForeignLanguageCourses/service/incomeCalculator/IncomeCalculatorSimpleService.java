package org.sinhro.ForeignLanguageCourses.service.incomeCalculator;

import org.sinhro.ForeignLanguageCourses.domain.Language;
import org.springframework.stereotype.Component;

@Component
public class IncomeCalculatorSimpleService implements IIncomeCalculator {

    @Override
    public Integer calculate(Language language, Boolean isPrivate) {
        int profit = (language.getId() + 1) * 100;
        profit = (int) (profit * (isPrivate ? 1.3 : 1));
        return profit;
    }
}
