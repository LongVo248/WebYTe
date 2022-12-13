package mtt.webyte.mapper;

import mtt.webyte.dto.AppointmentScheduleDTO;
import mtt.webyte.dto.PrescriptionDTO;
import mtt.webyte.model.AppointmentSchedule;
import mtt.webyte.model.Prescription;
import mtt.webyte.model.PrescriptionMedical;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PrescriptionMedicalMapper extends AbstractMapper<PrescriptionDTO, PrescriptionMedical> {
    PrescriptionMedicalMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(PrescriptionMedicalMapper.class);
}
