package org.sinhro.ForeignLanguageCourses.service.nameGenerator;

import org.sinhro.ForeignLanguageCourses.tools.NamesDataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Primary
public class PrettyNameGenerator implements INameGenerator {

    @Autowired
    private Random random;

    @Override
    public String generateSecondName() {
        return toPrettyString(NamesDataBase.getRandomFullName(random));
    }

    private String toPrettyString(NamesDataBase.FullName fname) {
        return fname.getName() + " " + fname.getSurname();
    }
}
