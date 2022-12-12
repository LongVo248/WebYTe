package mtt.webyte.repository;

import mtt.webyte.model.AppointmentSchedule;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentScheduleRepository extends JpaRepository<AppointmentSchedule, Long> {
	@Query("select s from AppointmentSchedule s where s.doctor.userId = :id and s.appointmentDate = :date")
	List<AppointmentSchedule> findApointment(@Param("id") Long id, @Param("date") Date date); 

	@Query("select s from AppointmentSchedule s where s.doctor.userId = :id")
	List<AppointmentSchedule> findApointmentOfDoctor(@Param("id") Long id); 
}
