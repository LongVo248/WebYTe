package mtt.webyte.mapper;

import mtt.webyte.dto.QuestionDTO;
import mtt.webyte.dto.ScheduleDTO;
import mtt.webyte.model.Question;
import mtt.webyte.model.Schedule;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScheduleMapper extends AbstractMapper<ScheduleDTO, Schedule> {
    ScheduleMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(ScheduleMapper.class);
}
