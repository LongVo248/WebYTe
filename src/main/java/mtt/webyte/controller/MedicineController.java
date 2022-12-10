package mtt.webyte.controller;

import javax.transaction.SystemException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import mtt.webyte.dto.MedicineDTO;
import mtt.webyte.dto.MessageResponse;
import mtt.webyte.dto.NewsDTO;
import mtt.webyte.services.impl.MedicineServiceImpl;
import mtt.webyte.services.impl.NewsServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/webyte/medicine")
public class MedicineController {

	private final Logger logger = org.slf4j.LoggerFactory.getLogger(AuthenticationRestController.class);
	private static final String BLID = "CTL-ACC";

	@Autowired
	MedicineServiceImpl service;

	@PostMapping("/")
	public ResponseEntity<?> createNews(@Valid @RequestBody MedicineDTO dto) throws SystemException{
		return ResponseEntity.ok(service.save(dto)); 
	}

	@PutMapping("/")
	public ResponseEntity<?> updateNews(@Valid @RequestBody MedicineDTO dto) throws SystemException{
		return ResponseEntity.ok(service.save(dto)); 
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteNews(@PathVariable Long id) throws SystemException{
		service.deleteMedicine(id);
		return ResponseEntity.ok(new MessageResponse("ok")); 
	}

	@GetMapping("/find-all")
	public ResponseEntity<?> findNews(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "") String title) throws SystemException{
		return ResponseEntity.ok(service.find(page, size, title)); 
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findOneNews(@PathVariable Long id) throws SystemException{
		return ResponseEntity.ok(service.findOne(id)); 
	}
}
