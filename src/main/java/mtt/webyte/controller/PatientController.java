
package mtt.webyte.controller;

import javax.transaction.SystemException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import mtt.webyte.dto.DepartmentDTO;
import mtt.webyte.dto.DoctorDTO;
import mtt.webyte.dto.MessageResponse;
import mtt.webyte.services.impl.DepartmentServiceImpl;
import mtt.webyte.services.impl.DoctorServiceImpl;
import mtt.webyte.services.impl.UserServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/webyte/patient")
public class PatientController {

	private final Logger logger = org.slf4j.LoggerFactory.getLogger(AuthenticationRestController.class);
	private static final String BLID = "CTL-ACC";

	@Autowired
	UserServiceImpl userServiceImpl;


	@GetMapping("/find-all")
	public ResponseEntity<?> findDoctors(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "") String name) throws SystemException{
		return ResponseEntity.ok(userServiceImpl.findAllPatient()); 
	}
}
