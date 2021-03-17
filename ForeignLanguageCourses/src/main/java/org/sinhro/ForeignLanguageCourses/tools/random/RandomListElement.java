package org.sinhro.ForeignLanguageCourses.tools.random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class RandomListElement implements IListElement {

    @Autowired
    private Random random;

    @Override
    public <T1> T1 get(List<T1> list) {
        return list.get(random.nextInt(list.size()));
    }
}
