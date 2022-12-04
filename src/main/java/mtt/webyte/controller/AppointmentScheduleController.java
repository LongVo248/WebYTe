package mtt.webyte.controller;

import mtt.webyte.dto.AppointmentScheduleDTO;
import mtt.webyte.model.AppointmentSchedule;
import mtt.webyte.services.AppointmentScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/webyte/appointment-schedule")
public class AppointmentScheduleController {
    @Autowired
    AppointmentScheduleService appointmentScheduleService;
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
    public  AppointmentSchedule insertAppointment(@RequestBody AppointmentSchedule appointmentSchedule){
        if(appointmentSchedule!=null){
            AppointmentSchedule appointmentSchedule1= appointmentScheduleService.insertAppointmentSchedule(appointmentSchedule);
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
}
