package org.sinhro.ForeignLanguageCourses.service.income_calculator;

import org.sinhro.ForeignLanguageCourses.domain.Language;

public interface IIncomeCalculator {
    Integer calculate(Language language, Boolean isPrivate);
}
