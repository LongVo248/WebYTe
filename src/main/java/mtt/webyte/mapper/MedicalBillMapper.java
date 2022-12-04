package mtt.webyte.mapper;

import mtt.webyte.dto.AppointmentScheduleDTO;
import mtt.webyte.dto.MedicalBillDTO;
import mtt.webyte.model.AppointmentSchedule;
import mtt.webyte.model.MedicalBill;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MedicalBillMapper extends AbstractMapper<MedicalBillDTO, MedicalBill> {
    MedicalBillMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(MedicalBillMapper.class);
}
