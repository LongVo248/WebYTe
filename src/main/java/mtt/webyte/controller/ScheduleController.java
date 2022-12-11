package mtt.webyte.controller;
import java.util.List;

import javax.transaction.SystemException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import mtt.webyte.dto.QuestionDTO ;
import mtt.webyte.dto.ScheduleDTO;
import mtt.webyte.dto.SicknessDTO;
import mtt.webyte.services.impl.NewsServiceImpl;
import mtt.webyte.services.impl.SicknessServiceImpl;
import mtt.webyte.services.impl.QuestionServiceImpl;
import mtt.webyte.services.impl.ScheduleServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/webyte/schedule")
public class ScheduleController{

	private final Logger logger = org.slf4j.LoggerFactory.getLogger(AuthenticationRestController.class);
	private static final String BLID = "CTL-ACC";

	@Autowired
	ScheduleServiceImpl scheduleServiceImpl;

	@PostMapping("/{id}")
	public ResponseEntity<?> createSchedule(@Valid @RequestBody List<ScheduleDTO> dtos, @PathVariable Long id) throws SystemException{
		return ResponseEntity.ok(scheduleServiceImpl.createSchedules(dtos, id)); 
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getScheduleOfDoctor(@PathVariable Long id) throws SystemException{
		return ResponseEntity.ok(scheduleServiceImpl.getScheduleOfDoctor(id)); 
	}
}
