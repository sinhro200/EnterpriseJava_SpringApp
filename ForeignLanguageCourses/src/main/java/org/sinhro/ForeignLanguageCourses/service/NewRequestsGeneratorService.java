package org.sinhro.ForeignLanguageCourses.service;

import org.sinhro.ForeignLanguageCourses.domain.Request;
import org.sinhro.ForeignLanguageCourses.repository.IRequestRepository;
import org.sinhro.ForeignLanguageCourses.service.request_generator.IRequestGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class NewRequestsGeneratorService {

    private final Logger log = LoggerFactory.getLogger(NewRequestsGeneratorService.class);

    private final IRequestRepository requestRepository;
    @Autowired
    @Qualifier("usingOrderRequestGeneratorDynamic")
    private IRequestGenerator requestGenerator;

    public NewRequestsGeneratorService(
        IRequestRepository requestRepository,
        IRequestGenerator requestGenerator) {
        this.requestRepository = requestRepository;
    }

    public void generate() {
        List<Request> requests = requestGenerator.generate();
        log.info("Пришло " + requests.size() + " заявок.");
        for (Request request : requests) {
            log.debug("      Новая заявка " + request.prettyString());
            requestRepository.add(request);
        }
    }
}
