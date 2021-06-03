package org.sinhro.ForeignLanguageCourses.service.incomeCalculator;

import org.sinhro.ForeignLanguageCourses.domain.Language;

public interface IIncomeCalculator {
    Integer calculate(Language language, Boolean isPrivate);
}
