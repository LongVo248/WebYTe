package mtt.webyte.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
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
public class QuestionServiceImpl extends AbstractServiceImpl<QuestionRepository, QuestionMapper, QuestionDTO, Question>{
    private final QuestionRepository repository;
    private final TopicRepository topicRepository;
    private final UserRepository userRepository;
    private final QuestionMapper mapper;
    private final UserMapper userMapper;
    private final AnswerMapper answerMapper;

    @Override
	public QuestionDTO save(QuestionDTO dto) {
		Question question = mapper.toEntity(dto, getCycleAvoidingMappingContext());
		Topic topic = topicRepository.findById(dto.getTopicId()).get();
		User user = userRepository.findById(dto.getUserId()).get();

		question.setTopic(topic);
		question.setUser(user);

		repository.save(question);
		return mapper.toDto(question, getCycleAvoidingMappingContext());
	}

    public List<QuestionDTO> findQuestion(){
	List<Question> list = repository.findAll();


	List<QuestionDTO> questionDTOs = new ArrayList<>();
	for(Question question : list) {
		QuestionDTO dto = mapper.toDto(question, getCycleAvoidingMappingContext());
		UserDTO userDTO = userMapper.toDto(question.getUser(), getCycleAvoidingMappingContext());
		Set<Answer> answers = question.getAnswers();
		Set<AnswerDTO> answerDTOs = new HashSet<>();
		for (Answer answer : answers) {
			answerDTOs.add(answerMapper.toDto(answer, getCycleAvoidingMappingContext()));
		}


		userDTO.setPwd("");
		dto.setUserDTO(userDTO);
		dto.setAnswerDTOs(answerDTOs);
		questionDTOs.add(dto);
	}
	return questionDTOs;
    }

    public QuestionDTO findOneSickness(Long id){
	Question question = repository.findById(id).get();
	return mapper.toDto(question, getCycleAvoidingMappingContext());
    }
}

