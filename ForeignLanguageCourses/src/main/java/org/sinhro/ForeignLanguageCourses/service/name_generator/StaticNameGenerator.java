package org.sinhro.ForeignLanguageCourses.service.name_generator;

import org.springframework.stereotype.Component;

@Component
public class StaticNameGenerator implements INameGenerator{
    @Override
    public String generateSecondName() {
        return "simple second name";
    }
}
