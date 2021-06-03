package org.sinhro.ForeignLanguageCourses.repository;

import org.sinhro.ForeignLanguageCourses.domain.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticRepository extends JpaRepository<Statistic, Integer> {
    @Query(value = "SELECT distinct * FROM statistic ORDER BY week_number DESC LIMIT 1 ", nativeQuery = true)
    Statistic lastStatistic();
}
