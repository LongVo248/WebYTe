package mtt.webyte.mapper;

import mtt.webyte.dto.NewsDTO;
import mtt.webyte.model.News;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NewsMapper extends AbstractMapper<NewsDTO, News> {
    NewsMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(NewsMapper.class);
    
}
