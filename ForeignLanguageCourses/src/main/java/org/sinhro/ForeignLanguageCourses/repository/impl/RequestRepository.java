package org.sinhro.ForeignLanguageCourses.repository.impl;

import org.sinhro.ForeignLanguageCourses.domain.Request;
import org.sinhro.ForeignLanguageCourses.repository.IRequestRepository;
import org.sinhro.ForeignLanguageCourses.tools.Counter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RequestRepository implements IRequestRepository {
    private final List<Request> requests = new ArrayList<>();


    @Override
    public List<Request> requests() {
        return new ArrayList<>(requests);
    }

    @Override
    public Request getById(Integer id) {
        return requests.stream()
            .filter(r -> r.getId().equals(id))
            .findAny()
            .orElse(null);
    }

    @Override
    public Request removeById(Integer id) {
        Request res = getById(id);
        requests.remove(res);
        return res;
    }

    @Override
    public void remove(Request request) {
        requests.remove(request);
    }

    @Override
    public void add(Request request) {
        if (request.getId() == null)
            request.setId(Counter.valueThenInc(this));
        requests.add(request);
    }
}
