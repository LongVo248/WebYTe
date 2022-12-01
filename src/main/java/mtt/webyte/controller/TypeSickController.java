package mtt.webyte.controller;

import javax.transaction.SystemException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import mtt.webyte.dto.NewsDTO;
import mtt.webyte.dto.SicknessDTO;
import mtt.webyte.services.impl.NewsServiceImpl;
import mtt.webyte.services.impl.SicknessServiceImpl;
import mtt.webyte.services.impl.TypeSickServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/webyte/type-sick")
public class TypeSickController {

	private final Logger logger = org.slf4j.LoggerFactory.getLogger(AuthenticationRestController.class);
	private static final String BLID = "CTL-ACC";

	@Autowired
	TypeSickServiceImpl sicknessService;

	@GetMapping("/find-all")
	public ResponseEntity<?> findNews() throws SystemException{
		return ResponseEntity.ok(sicknessService.findAll()); 
	}

}
