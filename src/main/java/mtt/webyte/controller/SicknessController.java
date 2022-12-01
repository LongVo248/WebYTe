
package mtt.webyte.controller;

import javax.transaction.SystemException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import mtt.webyte.dto.MessageResponse;
import mtt.webyte.dto.NewsDTO;
import mtt.webyte.dto.SicknessDTO;
import mtt.webyte.services.impl.NewsServiceImpl;
import mtt.webyte.services.impl.SicknessServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/webyte/sickness")
public class SicknessController {

	private final Logger logger = org.slf4j.LoggerFactory.getLogger(AuthenticationRestController.class);
	private static final String BLID = "CTL-ACC";

	@Autowired
	SicknessServiceImpl sicknessService;

	@PostMapping("/")
	public ResponseEntity<?> createNews(@Valid @RequestBody SicknessDTO sicknessDTO) throws SystemException{
		return ResponseEntity.ok(sicknessService.save(sicknessDTO)); 
	}

	@PutMapping("/")
	public ResponseEntity<?> updateNews(@Valid @RequestBody SicknessDTO sicknessDTO) throws SystemException{
		return ResponseEntity.ok(sicknessService.save(sicknessDTO)); 
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteNews(@PathVariable Long id) throws SystemException{
		sicknessService.delete(id);
		return ResponseEntity.ok(new MessageResponse("ok")); 
	}

	@GetMapping("/find-all")
	public ResponseEntity<?> findNews(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "") String title) throws SystemException{
		return ResponseEntity.ok(sicknessService.findSickness(page, size, title)); 
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findOneNews(@PathVariable Long id) throws SystemException{
		return ResponseEntity.ok(sicknessService.findOneSickness(id)); 
	}
}
