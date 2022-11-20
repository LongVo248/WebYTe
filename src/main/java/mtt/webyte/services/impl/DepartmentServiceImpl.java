package mtt.webyte.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import mtt.webyte.dto.DepartmentDTO;
import mtt.webyte.mapper.DepartmentMapper;
import mtt.webyte.model.Department;
import mtt.webyte.repository.DepartmentRepository;

@Service
@AllArgsConstructor
@Getter
@Setter
public class DepartmentServiceImpl extends AbstractServiceImpl<DepartmentRepository, DepartmentMapper, DepartmentDTO, Department>{
    private final DepartmentRepository repository;
    private final DepartmentMapper mapper;

    @Override
	public DepartmentDTO save(DepartmentDTO dto) {
		Department department = mapper.toEntity(dto, getCycleAvoidingMappingContext());
		repository.save(department);
		repository.save(department);
		return dto;
	}

    public List<DepartmentDTO> findDepartment(Integer page, Integer size, String title){
	Pageable pagination = getPageable(page, size);
	List<Department> list = repository.findByDepartmentNameContaining(title, pagination).toList();
	List<DepartmentDTO> departmentDTOs = new ArrayList<>();
	for(Department department: list) {
		departmentDTOs.add(mapper.toDto(department, getCycleAvoidingMappingContext()));
	}
	return departmentDTOs;
    }

    public DepartmentDTO findOneDepartment(Long id){
	Department department = repository.findById(id).get();
	return mapper.toDto(department, getCycleAvoidingMappingContext());
    }
}

