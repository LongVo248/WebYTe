package mtt.webyte.controller;
import javax.transaction.SystemException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import mtt.webyte.dto.AnswerDTO;
import mtt.webyte.dto.QuestionDTO ;
import mtt.webyte.dto.SicknessDTO;
import mtt.webyte.model.Answer;
import mtt.webyte.services.impl.AnswerServiceImpl;
import mtt.webyte.services.impl.NewsServiceImpl;
import mtt.webyte.services.impl.SicknessServiceImpl;
import mtt.webyte.services.impl.QuestionServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/webyte/answer")
public class AnswerController {

	private final Logger logger = org.slf4j.LoggerFactory.getLogger(AuthenticationRestController.class);
	private static final String BLID = "CTL-ACC";

	@Autowired
	AnswerServiceImpl answerServiceImpl;

	@PostMapping("/")
	public ResponseEntity<?> createQuestion(@Valid @RequestBody AnswerDTO answer) throws SystemException{
		return ResponseEntity.ok(answerServiceImpl.save(answer)); 
	}

}
