package org.sinhro.ForeignLanguageCourses.tools;

import org.sinhro.ForeignLanguageCourses.domain.Intensity;
import org.sinhro.ForeignLanguageCourses.domain.Language;
import org.sinhro.ForeignLanguageCourses.domain.Level;

import java.util.Arrays;
import java.util.List;

public class RepositoryInitializers {

    public static List<Language> languages(Object repo) {
        return Arrays.asList(
            new Language(Counter.valueThenInc(repo), "Английский"),
            new Language(Counter.valueThenInc(repo), "Французский"),
            new Language(Counter.valueThenInc(repo), "Немецкий"),
            new Language(Counter.valueThenInc(repo), "Японский")
        );
    }

    public static List<Intensity> intensities(Object repo) {
        return Arrays.asList(
            new Intensity(Counter.valueThenInc(repo), "Интенсив",3),
            new Intensity(Counter.valueThenInc(repo), "Обычное обучение",2),
            new Intensity(Counter.valueThenInc(repo), "Поддерживающее обучение",1)
        );
    }

    public static List<Level> levels(Object repo) {
        return Arrays.asList(
            new Level(Counter.valueThenInc(repo), "Начальный"),
            new Level(Counter.valueThenInc(repo), "Средний"),
            new Level(Counter.valueThenInc(repo), "Продвинутый")
        );
    }
}
