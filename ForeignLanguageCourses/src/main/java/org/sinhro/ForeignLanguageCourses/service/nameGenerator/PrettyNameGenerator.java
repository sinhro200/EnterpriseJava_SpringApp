package org.sinhro.ForeignLanguageCourses.service.nameGenerator;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class PrettyNameGenerator implements INameGenerator {
    @Override
    public String generateSecondName() {
        return null;
    }
}
