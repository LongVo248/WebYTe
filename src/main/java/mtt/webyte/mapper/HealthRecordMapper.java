package mtt.webyte.mapper;

import mtt.webyte.dto.HealthRecordDTO;
import mtt.webyte.model.HealthRecord;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HealthRecordMapper extends AbstractMapper<HealthRecordDTO, HealthRecord> {
    HealthRecordMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(HealthRecordMapper.class);
}
