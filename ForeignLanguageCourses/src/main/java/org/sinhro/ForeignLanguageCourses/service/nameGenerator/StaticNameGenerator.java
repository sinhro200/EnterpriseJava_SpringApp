package org.sinhro.ForeignLanguageCourses.service.nameGenerator;

import org.springframework.stereotype.Component;

@Component
public class StaticNameGenerator implements INameGenerator{
    @Override
    public String generateSecondName() {
        return "simple second name";
    }
}
