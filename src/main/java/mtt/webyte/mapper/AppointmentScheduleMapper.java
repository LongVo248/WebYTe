package mtt.webyte.mapper;

import mtt.webyte.dto.AppointmentScheduleDTO;
import mtt.webyte.model.AppointmentSchedule;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppointmentScheduleMapper extends AbstractMapper<AppointmentScheduleDTO, AppointmentSchedule> {
    AppointmentScheduleMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(AppointmentScheduleMapper.class);
}
