package mtt.webyte.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import mtt.webyte.dto.AnswerDTO;
import mtt.webyte.dto.NewsDTO;
import mtt.webyte.dto.QuestionDTO;
import mtt.webyte.dto.SicknessDTO;
import mtt.webyte.dto.UserDTO;
import mtt.webyte.mapper.AnswerMapper;
import mtt.webyte.mapper.NewsMapper;
import mtt.webyte.mapper.QuestionMapper;
import mtt.webyte.mapper.UserMapper ;
import mtt.webyte.model.Answer;
import mtt.webyte.model.News;
import mtt.webyte.model.Question;
import mtt.webyte.model.Sickness;
import mtt.webyte.model.Symptom;
import mtt.webyte.model.Topic;
import mtt.webyte.model.TypeSick;
import mtt.webyte.model.User;
import mtt.webyte.repository.AnswerRepository;
import mtt.webyte.repository.NewsRepository;
import mtt.webyte.repository.QuestionRepository;
import mtt.webyte.repository.SicknessRepository;
import mtt.webyte.repository.SymptonRepository;
import mtt.webyte.repository.UserRepository ;
import mtt.webyte.repository.TopicRepository ;

@Service
@AllArgsConstructor
@Getter
@Setter
public class AnswerServiceImpl extends AbstractServiceImpl<AnswerRepository, AnswerMapper, AnswerDTO, Answer>{
    private final AnswerRepository repository;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final AnswerMapper mapper;

    @Override
	public AnswerDTO save(AnswerDTO dto) {
		Answer answer = mapper.toEntity(dto, getCycleAvoidingMappingContext());
		Question question = questionRepository.findById(dto.getQuestionId()).get();
		User user = userRepository.findById(dto.getUserId()).get();

		answer.setQuestion(question);
		answer.setUser(user);

		repository.save(answer);
		return mapper.toDto(answer, getCycleAvoidingMappingContext());
	}
}

