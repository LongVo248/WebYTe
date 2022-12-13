package mtt.webyte.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.SystemException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import mtt.webyte.dto.DepartmentDTO;
import mtt.webyte.dto.DoctorDTO;
import mtt.webyte.dto.MessageResponse;
import mtt.webyte.dto.UserDTO;
import mtt.webyte.model.User;
import mtt.webyte.services.impl.DepartmentServiceImpl;
import mtt.webyte.services.impl.DoctorServiceImpl;
import mtt.webyte.services.impl.Excel;

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

@GetMapping("/export-to-excel")
    public void exportIntoExcelFile(HttpServletResponse response, @RequestParam() String from, @RequestParam() String to, @RequestParam(required = false) Long id) throws IOException {

		try {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=doctor" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<DoctorDTO> listOfStudents = doctorService.findDoctorWithSchedule(0, 1000, "", id);
        Excel generator = new Excel(listOfStudents, new SimpleDateFormat("yyyy-MM-dd").parse(from), new SimpleDateFormat("yyyy-MM-dd").parse(to));
        generator.generateExcelFile(response);
		} catch (ParseException ex) {
		}
    }

}
