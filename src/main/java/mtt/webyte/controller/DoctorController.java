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

	@PutMapping("/")
	public ResponseEntity<?> update(@Valid @RequestBody DoctorDTO doctorDTO) throws SystemException{
		return ResponseEntity.ok(doctorService.update(doctorDTO)); 
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) throws SystemException{
		doctorService.delete(id);	
		return ResponseEntity.ok(new MessageResponse("ok")); 
	}

	@GetMapping("/find-all")
	public ResponseEntity<?> findDoctors(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "") String name) throws SystemException{
		return ResponseEntity.ok(doctorService.find(page, size, name)); 
	}

	@GetMapping("/department/{id}")
	public ResponseEntity<?> findDoctors(@PathVariable Long id) throws SystemException{
		return ResponseEntity.ok(doctorService.findDoctorOfDepartment(id)); 
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findOneDoctor(@PathVariable Long id) throws SystemException{
		return ResponseEntity.ok(doctorService.findOne(id)); 
	}

}
