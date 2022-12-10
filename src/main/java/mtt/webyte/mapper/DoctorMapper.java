package mtt.webyte.mapper;

import mtt.webyte.dto.DoctorDTO;
import mtt.webyte.model.User;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DoctorMapper extends AbstractMapper<DoctorDTO, User> {
    DoctorMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(DoctorMapper.class); 

	void update(@MappingTarget User user, DoctorDTO dto);
}
