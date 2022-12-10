package mtt.webyte.mapper;

import mtt.webyte.dto.NewsDTO;
import mtt.webyte.dto.SicknessDTO;
import mtt.webyte.model.News;
import mtt.webyte.model.Sickness;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SicknessMapper extends AbstractMapper<SicknessDTO, Sickness> {
    SicknessMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(SicknessMapper.class);
}
