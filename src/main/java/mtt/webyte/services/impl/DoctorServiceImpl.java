package mtt.webyte.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.SystemException;

import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import mtt.webyte.dto.DepartmentDTO;
import mtt.webyte.dto.DoctorDTO;
import mtt.webyte.dto.MedicineDTO;
import mtt.webyte.dto.ScheduleDTO;
import mtt.webyte.enums.RoleType;
import mtt.webyte.mapper.DepartmentMapper;
import mtt.webyte.mapper.DoctorMapper;
import mtt.webyte.mapper.MedicineMapper;
import mtt.webyte.mapper.ScheduleMapper;
import mtt.webyte.model.Department;
import mtt.webyte.model.Medicine;
import mtt.webyte.model.Schedule;
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
	private final DepartmentMapper departmentMapper;
	private final ScheduleMapper scheduleMapper;
	private final PasswordEncoder passwordEncoder;
	private final MailService mailService;


    @Override
	public DoctorDTO save(DoctorDTO dto) {
		User doctor = mapper.toEntity(dto, getCycleAvoidingMappingContext());
		//@TODO generate random password and send email
		String password = genRandomPassword(6);
		doctor.setPwd(passwordEncoder.encode(password));
		doctor.setRole(RoleType.ROLE_DOCTOR);
		repository.save(doctor);
		mailService.sendSimpleMessage(dto.getEmail(),"doctor password", password);
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
		if (entity.getRole() == RoleType.ROLE_DOCTOR) {
			DoctorDTO doctor = mapper.toDto(entity, getCycleAvoidingMappingContext());
			Set<Department> departments = entity.getDepartment();
			List<DepartmentDTO> departmentDTOs = new ArrayList<>();
			for (Department department : departments) {
				departmentDTOs.add(departmentMapper.toDto(department, getCycleAvoidingMappingContext()));	
			}	
			doctor.setDepartmentDTOs(departmentDTOs);
			dtos.add(doctor);
		}		
	}
	return dtos;
    }

    public List<DoctorDTO> findDoctorWithSchedule(Integer page, Integer size, String name, Long id){
	Pageable pagination = getPageable(page, size);
	List<User> doctors = new ArrayList<>();

	if(id != null) {
		User doctor = repository.findById(id).get();
		doctors.add(doctor);
	} else {
	 	doctors = repository.findByUserFNameContainingOrUserLNameContainingAndRole(name, name, RoleType.ROLE_DOCTOR, pagination).toList();
	}

	List<DoctorDTO> dtos = new ArrayList<>();
	for(User entity: doctors) {
		if (entity.getRole() == RoleType.ROLE_DOCTOR) {
			DoctorDTO doctor = mapper.toDto(entity, getCycleAvoidingMappingContext());
			Set<Schedule> schedules = entity.getSchedules();
			List<ScheduleDTO> scheduleDTOs = new ArrayList<>();
			for (Schedule schedule : schedules) {
				scheduleDTOs.add(scheduleMapper.toDto(schedule, getCycleAvoidingMappingContext()));	
			}	
			doctor.setScheduleDTOs(scheduleDTOs);
			dtos.add(doctor);
		}		
	}
	return dtos;
    }

    public List<DoctorDTO> findDoctorOfDepartment(Long departmentId){
	Department department = departmentRepository.findById(departmentId).get();
	Set<User> doctors = department.getUsers();
	List<DoctorDTO> dtos = new ArrayList<>();
	for(User entity: doctors) {
		if (entity.getRole() == RoleType.ROLE_DOCTOR) {
			dtos.add(mapper.toDto(entity, getCycleAvoidingMappingContext()));
		}		
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

	public String genRandomPassword(int n) {
		String AlphaNumericString = "0123456789";
StringBuilder sb = new StringBuilder(n);

for (int i = 0; i < n; i++) {

int index = (int)(AlphaNumericString.length() * Math.random());

sb.append(AlphaNumericString.charAt(index));
}

return sb.toString();
	}
}

