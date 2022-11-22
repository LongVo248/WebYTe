package mtt.webyte.controller;

import javax.transaction.SystemException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import mtt.webyte.dto.DepartmentDTO;
import mtt.webyte.dto.DoctorDTO;
import mtt.webyte.services.impl.DepartmentServiceImpl;
import mtt.webyte.services.impl.DoctorServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/webyte/doctor")
public class DoctorController {

	private final Logger logger = org.slf4j.LoggerFactory.getLogger(AuthenticationRestController.class);
	private static final String BLID = "CTL-ACC";

	@Autowired
	DoctorServiceImpl doctorService;

	@PostMapping("/")
	public ResponseEntity<?> create(@Valid @RequestBody DoctorDTO doctorDTO) throws SystemException{
		return ResponseEntity.ok(doctorService.save(doctorDTO)); 
	}
}
