package mtt.webyte.controller;

import mtt.webyte.dto.AppointmentScheduleDTO;
import mtt.webyte.dto.MessageResponse;
import mtt.webyte.model.AppointmentSchedule;
import mtt.webyte.services.AppointmentScheduleService;
import mtt.webyte.services.impl.AppointmentScheduleServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.SystemException;
import javax.websocket.server.PathParam;

@CrossOrigin
@RestController
@RequestMapping("/webyte/appointment-schedule")
public class AppointmentScheduleController {
    @Autowired
    AppointmentScheduleService appointmentScheduleService;

    @Autowired
    AppointmentScheduleServiceImpl appointmentScheduleServiceImpl;
    @GetMapping()
    public List<AppointmentSchedule> getAllAppointment(){
        List<AppointmentSchedule> listAppointment= appointmentScheduleService.getAllListAppointmentSchedule();
        return listAppointment;
    }
    @GetMapping("/all")
    public List<AppointmentScheduleDTO> getAllAppoint() throws ParseException {
        List<AppointmentScheduleDTO> listAppointment=  appointmentScheduleService.getAllAppointmentSchedule();
        return listAppointment;
    }

    @PutMapping("/update-done/{id}")
    public boolean updateDone(@PathVariable() Long id) throws ParseException {
        return appointmentScheduleServiceImpl.updateAppointmentDone(id);
    }

    @PutMapping("/update-complete/{id}")
    public boolean updateComplete(@PathVariable() Long id) throws ParseException {
        return appointmentScheduleServiceImpl.updateAppointmentComplete(id);
    }

    @GetMapping("/user/{id}")
    public List<AppointmentScheduleDTO> getAppointDateOfuser(@PathVariable("id")Long id){
        return appointmentScheduleService.findAllOfUser(id);
    }

    @GetMapping("/allByDoctor/{id}")
    public List<AppointmentScheduleDTO> getAllAppoint(@PathVariable("id") int id){
        List<AppointmentScheduleDTO> listAppointment=  appointmentScheduleService.getAllAppointmentScheduleByDoctor(id);
        return listAppointment;
    }

    @GetMapping("/{id}")
    public AppointmentSchedule getAppointment(@PathVariable("id") int id){
        if(id>0){
            AppointmentSchedule appointmentSchedule= appointmentScheduleService.getAppointmentSchedule(id);
            return  appointmentSchedule;
        }
        return null;
    }
    @GetMapping("/appointById/{id}")
    public AppointmentScheduleDTO getAppointmentById(@PathVariable("id") int id){
        System.out.println(id);
        if(id>0){
            AppointmentScheduleDTO appointmentSchedule= appointmentScheduleService.getAppointmentScheduleById(id);
            return  appointmentSchedule;
        }
        return null;
    }

    @PostMapping()
    public  AppointmentScheduleDTO insertAppointment(@RequestBody AppointmentScheduleDTO dto){
        if(dto!=null){
            AppointmentScheduleDTO appointmentSchedule1= appointmentScheduleService.save(dto);
            return  appointmentSchedule1;
        }
        return null;
    }
    @PutMapping("")
    public AppointmentSchedule updateAppointmen(@RequestBody AppointmentSchedule appointmentSchedule){
        if(appointmentSchedule != null){
            AppointmentSchedule appointmentSchedule1= appointmentScheduleService.updateAppointmentSchedule(appointmentSchedule);
            return  appointmentSchedule1;
        }
        return  null;
    }
    @DeleteMapping("{id}")
    public int deleteAppointment(@PathVariable("id") int id){
        if(id>0){
            int success= appointmentScheduleService.deleteAppointmentSchedule(id);
            return  success;
        }
        else {
            return  0;
        }
    }

    @GetMapping("/count-time/{date}")
    public List<Map<String,Object>> getAllCountTime(@PathVariable("date") String date1) throws ParseException {
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(date1);
        Date date = formatter2.parse(date1);
        List<Map<String,Object>> listCountTime= appointmentScheduleService.getCountTimeFull(date);
        return  listCountTime;
    }

    @PutMapping("/update-status/{id}/{trangthai}")
    public  AppointmentSchedule updateStatus(@PathVariable("id")int id,@PathVariable("trangthai")String status){
        System.err.println(id + " - " + status);
        return  appointmentScheduleService.updateStatus(id, status);
    }

    @GetMapping("/getAppointDate{id}")
    public Map<String,Object> getAppointDate(@PathVariable("id")int id){
        Map<String,Object> map = appointmentScheduleService.getAppointDate(id);
        return  map;
    }

	@GetMapping("/check-date/{id}")
	public ResponseEntity<?> getScheduleOfDoctorAndDate(@PathVariable Long id, @PathParam("date") String date) throws SystemException{
		try {
			return ResponseEntity.ok(appointmentScheduleServiceImpl.getApointmentByDateAndDoctor(id, 
					new SimpleDateFormat("yyyy-MM-dd").parse(date))); 
		} catch (ParseException ex) {
			return ResponseEntity.ok(new MessageResponse("bad request")); 
		}
	}

	@GetMapping("/doctor/{id}")
	public ResponseEntity<?> getAppointmentOfDoctor(@PathVariable Long id) throws SystemException{
		return ResponseEntity.ok(appointmentScheduleServiceImpl.getAppointmentOfDoctor(id)); 
	}
}
