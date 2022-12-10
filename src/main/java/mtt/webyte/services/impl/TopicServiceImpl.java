package mtt.webyte.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import mtt.webyte.dto.NewsDTO;
import mtt.webyte.dto.SicknessDTO;
import mtt.webyte.dto.TopicDTO;
import mtt.webyte.mapper.NewsMapper;
import mtt.webyte.mapper.SicknessMapper;
import mtt.webyte.mapper.TopicMapper;
import mtt.webyte.mapper.TypeSickMapper;
import mtt.webyte.model.News;
import mtt.webyte.model.Sickness;
import mtt.webyte.model.Symptom;
import mtt.webyte.model.Topic;
import mtt.webyte.model.User;
import mtt.webyte.repository.NewsRepository;
import mtt.webyte.repository.SicknessRepository;
import mtt.webyte.repository.SymptonRepository;
import mtt.webyte.repository.TopicRepository;
import mtt.webyte.repository.TypeSickRepository;
import mtt.webyte.repository.UserRepository;

@Service
@AllArgsConstructor
@Getter
@Setter
public class TopicServiceImpl extends AbstractServiceImpl<TopicRepository, TopicMapper, TopicDTO, Topic>{
    private final TopicRepository repository;
    private final TopicMapper mapper;

    public List<TopicDTO> findTopic(){
	List<Topic> list = repository.findAll();
	List<TopicDTO> dtoList = new ArrayList<TopicDTO>() ;
	for (Topic type : list) {
		dtoList.add(mapper.toDto(type, getCycleAvoidingMappingContext()));
	}
	return dtoList;
    }

}

