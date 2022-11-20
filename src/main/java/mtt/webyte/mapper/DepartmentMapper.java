package mtt.webyte.mapper;

import mtt.webyte.dto.DepartmentDTO;
import mtt.webyte.model.Department;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper extends AbstractMapper<DepartmentDTO, Department> {
    NewsMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(NewsMapper.class); 
}
