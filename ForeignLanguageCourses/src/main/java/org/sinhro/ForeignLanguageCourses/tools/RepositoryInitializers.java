package org.sinhro.ForeignLanguageCourses.tools;

import org.sinhro.ForeignLanguageCourses.domain.Intensity;
import org.sinhro.ForeignLanguageCourses.domain.Language;
import org.sinhro.ForeignLanguageCourses.domain.Level;

import java.util.Arrays;
import java.util.List;

public class RepositoryInitializers {

    public static List<Language> initialLanguages() {
        return Arrays.asList(
            new Language("Английский"),
            new Language( "Французский"),
            new Language( "Немецкий"),
            new Language( "Японский")
        );
    }

    public static List<Intensity> initialIntensities() {
        return Arrays.asList(
            new Intensity( "Интенсив",3),
            new Intensity( "Обычное обучение",2),
            new Intensity( "Поддерживающее обучение",1)
        );
    }

    public static List<Level> initialLevels() {
        return Arrays.asList(
            new Level("Начальный"),
            new Level("Средний"),
            new Level("Продвинутый")
        );
    }
}
