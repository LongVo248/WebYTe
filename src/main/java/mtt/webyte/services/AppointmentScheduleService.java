package mtt.webyte.services;

import mtt.webyte.dto.AppointmentScheduleDTO;
import mtt.webyte.model.AppointmentSchedule;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface AppointmentScheduleService extends AbstractService<AppointmentScheduleDTO, AppointmentSchedule> {
    public List<AppointmentSchedule> getAllListAppointmentSchedule();
    public List<AppointmentScheduleDTO> getAllAppointmentSchedule() throws ParseException;
    public List<AppointmentScheduleDTO> getAllAppointmentScheduleByDoctor(int id);
    public AppointmentSchedule getAppointmentSchedule(int key);
    public AppointmentSchedule insertAppointmentSchedule(AppointmentSchedule appointmentSchedule);
    public AppointmentSchedule updateAppointmentSchedule(AppointmentSchedule appointmentSchedule);
    public int deleteAppointmentSchedule(int id);
    public  AppointmentSchedule selectTop1Appoint();
    public List<Map<String,Object>> getCountTimeFull(Date date);
    public AppointmentScheduleDTO getAppointmentScheduleById(int id);
    public AppointmentSchedule updateStatus(int id, String status);
    public  Map<String,Object> getAppointDate(int id);
	public List<AppointmentScheduleDTO> findAllOfUser(Long id);
}
