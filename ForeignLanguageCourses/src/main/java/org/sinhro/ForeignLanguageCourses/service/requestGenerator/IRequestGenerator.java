package org.sinhro.ForeignLanguageCourses.service.requestGenerator;

import org.sinhro.ForeignLanguageCourses.domain.Request;

import java.util.List;

public interface IRequestGenerator {
    List<Request> generate();
}
