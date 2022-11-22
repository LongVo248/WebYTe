package mtt.webyte.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.SystemException;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import mtt.webyte.dto.MedicineDTO;
import mtt.webyte.mapper.MedicineMapper;
import mtt.webyte.model.Medicine;
import mtt.webyte.repository.MedicineRepository;

@Service
@AllArgsConstructor
@Getter
@Setter
public class MedicineServiceImpl extends AbstractServiceImpl<MedicineRepository, MedicineMapper, MedicineDTO, Medicine>{
    private final MedicineRepository repository;
    private final MedicineMapper mapper;

    @Override
	public MedicineDTO save(MedicineDTO dto) {
		Medicine medicine = mapper.toEntity(dto, getCycleAvoidingMappingContext());
		repository.save(medicine);
		return dto;
	}

    public List<MedicineDTO> find(Integer page, Integer size, String title){
	Pageable pagination = getPageable(page, size);
	List<Medicine> list = repository.findByMedicineNameContaining(title, pagination).toList();
	List<MedicineDTO> dtos = new ArrayList<>();
	for(Medicine entity: list) {
		dtos.add(mapper.toDto(entity, getCycleAvoidingMappingContext()));
	}
	return dtos;
    }

    public MedicineDTO findOne(Long id){
	Medicine entity = repository.findById(id).get();
	return mapper.toDto(entity, getCycleAvoidingMappingContext());
    }

	public void deleteMedicine(long id) throws SystemException {
		Medicine medicine = repository.findById(id).get();	
		
		if (medicine.getPrescriptions().size() > 0) {	
			throw new SystemException("Can not delete this medicine");
		} 
    		super.delete(id);
	}
}

