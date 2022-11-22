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
		Set<Department> departments = departmentRepository.findByDepartmentIdIn(dto.getDepartmentId());
		//@TODO generate random password and send email
		doctor.setPwd("test");
		doctor.setRole(RoleType.ROLE_DOCTOR);
		User newDoctor = repository.save(doctor);
		for (Department department : departments) {
			Set<User> users = department.getUsers();
			users.add(newDoctor);
			department.setUsers(users);
		}
		departmentRepository.saveAll(departments);
		return dto;
	}
}

