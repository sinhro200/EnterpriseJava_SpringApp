package org.sinhro.ForeignLanguageCourses.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class RandomConfig {
    @Bean
    Random random() {
        return new Random();
    }
}
