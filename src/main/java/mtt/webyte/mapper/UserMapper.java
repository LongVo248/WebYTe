package mtt.webyte.mapper;

import mtt.webyte.dto.UserDTO;
import mtt.webyte.mapper.helper.CycleAvoidingMappingContext;
import mtt.webyte.model.User;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

public interface UserMapper extends AbstractMapper<UserDTO, User> {
    UserMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(UserMapper.class);

    @Override
    UserDTO toDto(User entity, CycleAvoidingMappingContext context);

    @Mappings({
            @Mapping(target = "pwd", ignore = true),
            @Mapping(target = "username", ignore = true),
            @Mapping(target = "email", ignore = true),
            @Mapping(target = "userFName", ignore = true),
            @Mapping(target = "userLName", ignore = true),
            @Mapping(target = "roleType", ignore = true),
            @Mapping(target = "phoneNum", ignore = true),
            @Mapping(target = "address", ignore = true),
            @Mapping(target = "birthDate", ignore = true),
            @Mapping(target = "image", ignore = true),
            @Mapping(target = "doctorName", ignore = true),
    })
    UserDTO toCurrentUserDto(User entity);
}
