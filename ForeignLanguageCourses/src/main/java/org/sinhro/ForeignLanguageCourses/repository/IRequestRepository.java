package org.sinhro.ForeignLanguageCourses.repository;

import org.sinhro.ForeignLanguageCourses.domain.Request;

import java.util.List;

public interface IRequestRepository {
    List<Request> requests();

    Request getById(Integer id);

    Request removeById(Integer id);

    void remove(Request request);

    void add(Request request);


}
