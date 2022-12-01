package mtt.webyte.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.SystemException;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import mtt.webyte.dto.DoctorDTO;
import mtt.webyte.dto.MedicineDTO;
import mtt.webyte.enums.RoleType;
import mtt.webyte.mapper.DoctorMapper;
import mtt.webyte.mapper.MedicineMapper;
import mtt.webyte.model.Department;
import mtt.webyte.model.Medicine;
import mtt.webyte.model.User;
import mtt.webyte.repository.DepartmentRepository;
import mtt.webyte.repository.MedicineRepository;
import mtt.webyte.repository.UserRepository;

@Service
@AllArgsConstructor
@Getter
@Setter
public class DoctorServiceImpl extends AbstractServiceImpl<UserRepository, DoctorMapper, DoctorDTO, User>{
    private final UserRepository repository;
    private final DepartmentRepository departmentRepository;
    private final DoctorMapper mapper;

    @Override
	public DoctorDTO save(DoctorDTO dto) {
		User doctor = mapper.toEntity(dto, getCycleAvoidingMappingContext());
		//@TODO generate random password and send email
		doctor.setPwd("test");
		doctor.setRole(RoleType.ROLE_DOCTOR);
		repository.save(doctor);
		return dto;
	}

	public DoctorDTO update(DoctorDTO dto) {
		User doctor = repository.findUserByUserId(dto.getUserId());
		mapper.update(doctor, dto);	
		repository.save(doctor);
		return dto;
	}

    public List<DoctorDTO> find(Integer page, Integer size, String name){
	Pageable pagination = getPageable(page, size);
	List<User> doctors = repository.findByUserFNameContainingOrUserLNameContainingAndRole(name, name, RoleType.ROLE_DOCTOR, pagination).toList();
	List<DoctorDTO> dtos = new ArrayList<>();
	for(User entity: doctors) {
		dtos.add(mapper.toDto(entity, getCycleAvoidingMappingContext()));
	}
	return dtos;
    }

    public DoctorDTO findOne(Long id){
	User entity = repository.findById(id).get();
	return mapper.toDto(entity, getCycleAvoidingMappingContext());
    }

	public void deleteDoctor(long id) throws SystemException {
    		super.delete(id);
	}
}

