package mtt.webyte.repository;


import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mtt.webyte.model.Question;
import mtt.webyte.model.Schedule;
import mtt.webyte.model.Topic;
import mtt.webyte.model.TypeSick;


@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
	List<Schedule> findAllByDayOfWeek(Date dayOfWeek);

	@Query("select s from Schedule s where s.dayOfWeek = :dayOfWeek and s.user.userId = :userId")
	List<Schedule> findSchedule(@Param("dayOfWeek") Date dateOfWeek, @Param("userId") Long id);
}
