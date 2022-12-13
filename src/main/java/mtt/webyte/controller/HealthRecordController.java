
package mtt.webyte.controller;

import javax.transaction.SystemException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import mtt.webyte.dto.CreatePrescriptionRequest;
import mtt.webyte.dto.MedicineDTO;
import mtt.webyte.dto.MessageResponse;
import mtt.webyte.dto.NewsDTO;
import mtt.webyte.services.impl.HealthRecordServiceImpl;
import mtt.webyte.services.impl.MedicineServiceImpl;
import mtt.webyte.services.impl.NewsServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/webyte/health-record")
public class HealthRecordController  {

	private final Logger logger = org.slf4j.LoggerFactory.getLogger(AuthenticationRestController.class);
	private static final String BLID = "CTL-ACC";

	@Autowired
	HealthRecordServiceImpl service;

	@PostMapping("/")
	public ResponseEntity<?> createNews(@Valid @RequestBody CreatePrescriptionRequest dto) throws SystemException{
		return ResponseEntity.ok(service.createHealthRecord(dto)); 
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getNews(@PathVariable Long id) throws SystemException{
		return ResponseEntity.ok(service.getHealthRecord(id)); 
	}

}
