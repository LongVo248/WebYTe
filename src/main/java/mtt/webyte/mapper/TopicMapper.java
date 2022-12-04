package mtt.webyte.mapper;

import mtt.webyte.dto.SymptomDTO;
import mtt.webyte.dto.TopicDTO;
import mtt.webyte.model.Symptom;
import mtt.webyte.model.Topic;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TopicMapper extends AbstractMapper<TopicDTO, Topic> {
    TopicMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(TopicMapper.class);
}
