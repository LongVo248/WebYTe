
package mtt.webyte.mapper;

import mtt.webyte.dto.NewsDTO;
import mtt.webyte.dto.SicknessDTO;
import mtt.webyte.dto.TypeSickDTO;
import mtt.webyte.model.News;
import mtt.webyte.model.Sickness;
import mtt.webyte.model.TypeSick;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TypeSickMapper extends AbstractMapper<TypeSickDTO, TypeSick> {
	TypeSickMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(TypeSickMapper.class);
}
