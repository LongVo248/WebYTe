package mtt.webyte.mapper;

import mtt.webyte.dto.AppointmentScheduleDTO;
import mtt.webyte.dto.PriceOfMedicalDTO;
import mtt.webyte.model.AppointmentSchedule;
import mtt.webyte.model.PriceOfMedical;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceOfMedicalMapper extends AbstractMapper<PriceOfMedicalDTO, PriceOfMedical> {
    PriceOfMedicalMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(PriceOfMedicalMapper.class);
}
