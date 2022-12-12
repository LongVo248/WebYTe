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
import mtt.webyte.dto.ScheduleDTO;
import mtt.webyte.dto.SicknessDTO;
import mtt.webyte.dto.TopicDTO;
import mtt.webyte.mapper.NewsMapper;
import mtt.webyte.mapper.ScheduleMapper;
import mtt.webyte.mapper.SicknessMapper;
import mtt.webyte.mapper.TopicMapper;
import mtt.webyte.mapper.TypeSickMapper;
import mtt.webyte.model.News;
import mtt.webyte.model.Schedule;
import mtt.webyte.model.Sickness;
import mtt.webyte.model.Symptom;
import mtt.webyte.model.Topic;
import mtt.webyte.model.User;
import mtt.webyte.repository.NewsRepository;
import mtt.webyte.repository.ScheduleRepository;
import mtt.webyte.repository.SicknessRepository;
import mtt.webyte.repository.SymptonRepository;
import mtt.webyte.repository.TopicRepository;
import mtt.webyte.repository.TypeSickRepository;
import mtt.webyte.repository.UserRepository;

@Service
@AllArgsConstructor
@Getter
@Setter
public class ScheduleServiceImpl extends AbstractServiceImpl<ScheduleRepository, ScheduleMapper, ScheduleDTO, Schedule>{
    private final ScheduleRepository repository;
    private final UserRepository userRepository;
    private final ScheduleMapper mapper;

    public List<ScheduleDTO> createSchedules(List<ScheduleDTO> scheduleDTOs, Long id){
	User user = userRepository.findByUserId(id);
	    	
	List<Schedule> schedules = new ArrayList<>();
	for (ScheduleDTO dto : scheduleDTOs) {
		Schedule schedule = mapper.toEntity(dto, getCycleAvoidingMappingContext());
		schedule.setUser(user);
		schedules.add(schedule);
	}
	List<Schedule> list = repository.saveAll(schedules);
	List<ScheduleDTO> dtoList = new ArrayList<>() ;
	for (Schedule type : list) {
		dtoList.add(mapper.toDto(type, getCycleAvoidingMappingContext()));
	}
	return dtoList;
    }


    public List<ScheduleDTO> getScheduleOfDoctor(Long id){
	User user = userRepository.findByUserId(id);
	Set<Schedule> schedules = user.getSchedules();
	List<Schedule> list = repository.saveAll(schedules);
	List<ScheduleDTO> dtoList = new ArrayList<>() ;
	for (Schedule type : list) {
		dtoList.add(mapper.toDto(type, getCycleAvoidingMappingContext()));
	}
	return dtoList;
    }
}

