package mtt.webyte.controller;

import javax.transaction.SystemException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import mtt.webyte.dto.AddDoctorRequest;
import mtt.webyte.dto.DepartmentDTO;
import mtt.webyte.dto.MessageResponse;
import mtt.webyte.services.impl.DepartmentServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/webyte/department")
public class DepartmentController {

	private final Logger logger = org.slf4j.LoggerFactory.getLogger(AuthenticationRestController.class);
	private static final String BLID = "CTL-ACC";

	@Autowired
	DepartmentServiceImpl departmentService;

	@PostMapping("/")
	public ResponseEntity<?> create(@Valid @RequestBody DepartmentDTO departmentDTO) throws SystemException{
		return ResponseEntity.ok(departmentService.save(departmentDTO)); 
	}

	@PutMapping("/")
	public ResponseEntity<?> update(@Valid @RequestBody DepartmentDTO departmentDTO) throws SystemException{
		return ResponseEntity.ok(departmentService.save(departmentDTO)); 
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) throws SystemException{
		departmentService.delete(id);
		return ResponseEntity.ok(new MessageResponse("Ok")); 
	}

	@GetMapping("/find-all")
	public ResponseEntity<?> findNews(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "") String title) throws SystemException{
		return ResponseEntity.ok(departmentService.findDepartment(page, size, title)); 
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findOneNews(@PathVariable Long id) throws SystemException{
		return ResponseEntity.ok(departmentService.findOneDepartment(id)); 
	}

	@PutMapping("/add-doctor")
	public ResponseEntity<?> addDoctor(@RequestBody AddDoctorRequest request) throws SystemException{
		return ResponseEntity.ok(departmentService.addDoctor(request)); 
	}

	@PutMapping("/remove-doctor")
	public ResponseEntity<?> removeDoctor(@RequestBody AddDoctorRequest request) throws SystemException{
		return ResponseEntity.ok(departmentService.removeDoctor(request)); 
	}
}
