package org.sinhro.ForeignLanguageCourses;

import org.sinhro.ForeignLanguageCourses.domain.Group;
import org.sinhro.ForeignLanguageCourses.repository.GroupRepository;
import org.sinhro.ForeignLanguageCourses.service.RepositoryInitializerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@EntityScan("org/sinhro/ForeignLanguageCourses/domain/")
@SpringBootApplication
public class SpringApplication implements CommandLineRunner {

    private final MainProcess mainProcess;

    @Value("${simulationTimeInTwoWeeks}")
    private int numberTwoWeeksInSimulation;

    @Autowired
    private RepositoryInitializerService repositoryInitializerService;

    @Autowired
    private GroupRepository gr;

    public SpringApplication(MainProcess mainProcess) {
        this.mainProcess = mainProcess;
    }

    public static void main(String[] args) {
        //        SpringApplication.run(ConsoleSpringBootApplication.class, args);

        ApplicationContext context =
            new AnnotationConfigApplicationContext(SpringApplication.class);

        context.getBean(SpringApplication.class).run(args);
    }

    @Override
    public void run(String... args)  {
        repositoryInitializerService.initIfNeeded();
        gr.save(new Group());
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
