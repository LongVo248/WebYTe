package mtt.webyte.mapper;

import mtt.webyte.dto.ScheduleDTO;
import mtt.webyte.dto.SymptomDTO;
import mtt.webyte.model.Schedule;
import mtt.webyte.model.Symptom;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SymptomMapper extends AbstractMapper<SymptomDTO, Symptom> {
    SymptomMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(SymptomMapper.class);
}
