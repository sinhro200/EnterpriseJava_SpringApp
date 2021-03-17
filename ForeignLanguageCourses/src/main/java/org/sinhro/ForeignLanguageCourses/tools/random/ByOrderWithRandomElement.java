package org.sinhro.ForeignLanguageCourses.tools.random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Возвращает случайный элемент
 * У элементов в начале списка шанс больше
 */
@Component
public class ByOrderWithRandomElement implements IListElement {

    @Autowired
    private Random random;

    @Override
    public <T> T get(List<T> list) {
        int len = list.size();
        List<Integer> positions = new ArrayList<>();
        for (int i = 0; i < len; i++)
            for (int j = 0; j < len - i; j++)
                positions.add(i);

        Integer position = positions.get(random.nextInt(positions.size()));
        return list.get(position);
    }
}
