package mtt.webyte.controller;

import javax.transaction.SystemException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import mtt.webyte.dto.NewsDTO;
import mtt.webyte.services.NewsService;
import mtt.webyte.services.impl.NewsServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/webyte/news")
public class NewsController {

	private final Logger logger = org.slf4j.LoggerFactory.getLogger(AuthenticationRestController.class);
	private static final String BLID = "CTL-ACC";

	@Autowired
	NewsServiceImpl newsService;

	@PostMapping("/")
	public ResponseEntity<?> createNews(@Valid @RequestBody NewsDTO newsDTO) throws SystemException{
		return ResponseEntity.ok(newsService.save(newsDTO)); 
	}

	@PutMapping("/")
	public ResponseEntity<?> updateNews(@Valid @RequestBody NewsDTO newsDTO) throws SystemException{
		return ResponseEntity.ok(newsService.save(newsDTO)); 
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteNews(@PathVariable Long id) throws SystemException{
		newsService.delete(id);
		return ResponseEntity.ok("ok"); 
	}

	@GetMapping("/find-all")
	public ResponseEntity<?> findNews(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "") String title) throws SystemException{
		return ResponseEntity.ok(newsService.findNews(page, size, title)); 
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findOneNews(@PathVariable Long id) throws SystemException{
		return ResponseEntity.ok(newsService.findOneNews(id)); 
	}
}
