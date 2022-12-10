package mtt.webyte.mapper;

import mtt.webyte.dto.UserDTO;
import mtt.webyte.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper extends AbstractMapper<UserDTO, User> {
    UserMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(UserMapper.class);

}
