package mtt.webyte.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import mtt.webyte.dto.NewsDTO;
import mtt.webyte.mapper.NewsMapper;
import mtt.webyte.model.News;
import mtt.webyte.model.User;
import mtt.webyte.repository.NewsRepository;
import mtt.webyte.repository.UserRepository;

@Service
@AllArgsConstructor
@Getter
@Setter
public class NewsServiceImpl extends AbstractServiceImpl<NewsRepository, NewsMapper, NewsDTO, News>{
    private final NewsRepository repository;
    private final UserRepository userRepository;
    private final NewsMapper mapper;

    @Override
	public NewsDTO save(NewsDTO dto) {
		User user = userRepository.findByUserId(dto.getUserID());
		News news = mapper.toEntity(dto, getCycleAvoidingMappingContext());
		news.setUser(user);
		repository.save(news);
		return dto;
	}

    public List<NewsDTO> findNews(Integer page, Integer size, String title){
	Pageable pagination = getPageable(page, size);
	List<News> list = repository.findByTitleContaining(title, pagination).toList();
	List<NewsDTO> newsDTOs = new ArrayList<>();
	for(News news : list) {
		newsDTOs.add(mapper.toDto(news, getCycleAvoidingMappingContext()));
	}
	return newsDTOs;
    }

    public NewsDTO findOneNews(Long id){
	News news = repository.findById(id).get();
	return mapper.toDto(news, getCycleAvoidingMappingContext());
    }
}

