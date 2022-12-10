package mtt.webyte.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import mtt.webyte.dto.AddDoctorRequest;
import mtt.webyte.dto.DepartmentDTO;
import mtt.webyte.enums.RoleType;
import mtt.webyte.mapper.DepartmentMapper;
import mtt.webyte.model.Department;
import mtt.webyte.model.User;
import mtt.webyte.repository.DepartmentRepository;
import mtt.webyte.repository.UserRepository;

@Service
@AllArgsConstructor
@Getter
@Setter
public class DepartmentServiceImpl extends AbstractServiceImpl<DepartmentRepository, DepartmentMapper, DepartmentDTO, Department>{
    private final DepartmentRepository repository;
    private final DepartmentMapper mapper;
    private final UserRepository userRepository; 

    @Override
	public DepartmentDTO save(DepartmentDTO dto) {
		Department department = mapper.toEntity(dto, getCycleAvoidingMappingContext());
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

    public DepartmentDTO addDoctor(AddDoctorRequest request){
	Department department = repository.findById(request.getDepartmentId()).get();
	Set<User> users = userRepository.findByUserIdInAndRole(request.getDoctorIds(), RoleType.ROLE_DOCTOR);
	department.setUsers(users);
	repository.save(department);
	return mapper.toDto(department, getCycleAvoidingMappingContext());
    }

    public DepartmentDTO removeDoctor(AddDoctorRequest request){
	Department department = repository.findById(request.getDepartmentId()).get();
	Set<User> doctors = userRepository.findByUserIdInAndRole(request.getDoctorIds(), RoleType.ROLE_DOCTOR);
	for (User doctor : doctors) {
		department.getUsers().remove(doctor);
	}
	repository.save(department);
	return mapper.toDto(department, getCycleAvoidingMappingContext());
    }
}

