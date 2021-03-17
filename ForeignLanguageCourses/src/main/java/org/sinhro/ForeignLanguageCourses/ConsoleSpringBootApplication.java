package org.sinhro.ForeignLanguageCourses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//Spring boot console app

@Configuration
@ComponentScan(
//    basePackages = "org.sinhro.ForeignLanguageCourses"
    basePackageClasses = {ConsoleSpringBootApplication.class}
)
public class ConsoleSpringBootApplication implements CommandLineRunner {

    private final MainProcess mainProcess;

    private static Logger logger = LoggerFactory.getLogger(ConsoleSpringBootApplication.class);

    @Value("${simulationTimeInTwoWeeks}")
    private int numberTwoWeeksInSimulation;

    public ConsoleSpringBootApplication(MainProcess mainProcess) {
        this.mainProcess = mainProcess;
    }

    public static void main(String[] args) {
        //        SpringApplication.run(ConsoleSpringBootApplication.class, args);

        ApplicationContext context =
            new AnnotationConfigApplicationContext(ConsoleSpringBootApplication.class);

        context.getBean(ConsoleSpringBootApplication.class).run(args);
    }

    @Override
    public void run(String... args)  {
        for (int i = 0; i < numberTwoWeeksInSimulation; i++)
            mainProcess.twoWeekTick();

        mainProcess.end();

    }

    /**
     * fixed delay in ms
     * 2 min = 2 * 60 * 1000 = 120 000
     */
//    @Scheduled(fixedDelay = 120000)
//    @Scheduled(fixedDelay = 60000)
//    public void twoWeeksEnded() {
//        mainProcess.twoWeekTick();
//    }
}
