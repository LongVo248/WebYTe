package mtt.webyte.mapper;

import mtt.webyte.dto.AnswerDTO;
import mtt.webyte.dto.AppointmentScheduleDTO;
import mtt.webyte.model.Answer;
import mtt.webyte.model.AppointmentSchedule;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnswerMapper extends AbstractMapper<AnswerDTO, Answer> {
    AnswerMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(AnswerMapper.class);
}
