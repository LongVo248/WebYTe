package mtt.webyte.mapper;

import mtt.webyte.dto.AppointmentScheduleDTO;
import mtt.webyte.dto.PrescriptionDTO;
import mtt.webyte.model.AppointmentSchedule;
import mtt.webyte.model.Prescription;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PrescriptionMapper extends AbstractMapper<PrescriptionDTO, Prescription> {
    PrescriptionMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(PrescriptionMapper.class);
}
