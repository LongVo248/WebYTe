
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
import mtt.webyte.mapper.NewsMapper;
import mtt.webyte.mapper.SicknessMapper;
import mtt.webyte.model.News;
import mtt.webyte.model.Sickness;
import mtt.webyte.model.Symptom;
import mtt.webyte.model.TypeSick;
import mtt.webyte.model.User;
import mtt.webyte.repository.NewsRepository;
import mtt.webyte.repository.SicknessRepository;
import mtt.webyte.repository.SymptonRepository;
import mtt.webyte.repository.TypeSickRepository;
import mtt.webyte.repository.UserRepository;

@Service
@AllArgsConstructor
@Getter
@Setter
public class SicknessServiceImpl extends AbstractServiceImpl<SicknessRepository, SicknessMapper, SicknessDTO, Sickness>{
    private final SicknessRepository repository;
    private final SymptonRepository symptonRepository;
	private final TypeSickRepository typeSickRepository;

    private final SicknessMapper mapper;

    @Override
	public SicknessDTO save(SicknessDTO dto) {
		Set<Symptom> symptoms = symptonRepository.findBySymptomIdIn(dto.getSymptomIds());
		TypeSick typeSick = typeSickRepository.findById(dto.getTypeSickId()).get();
		Sickness sickness = mapper.toEntity(dto, getCycleAvoidingMappingContext());
		sickness.setTypeSick(typeSick);
		sickness.setSymptoms(symptoms);
		repository.save(sickness);
		return mapper.toDto(sickness, getCycleAvoidingMappingContext());
	}

    public List<SicknessDTO> findSickness(Integer page, Integer size, String title){
	Pageable pagination = getPageable(page, size);
	List<Sickness> list = repository.findBySickNameContaining(title, pagination).toList();
	List<SicknessDTO> sicknessDTOs = new ArrayList<>();
	for(Sickness sickness : list) {
		sicknessDTOs.add(mapper.toDto(sickness, getCycleAvoidingMappingContext()));
	}
	return sicknessDTOs;
    }

    public SicknessDTO findOneSickness(Long id){
	Sickness sickness = repository.findById(id).get();
	return mapper.toDto(sickness, getCycleAvoidingMappingContext());
    }
}

