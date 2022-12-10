package mtt.webyte.mapper;

import mtt.webyte.dto.MedicineDTO;
import mtt.webyte.model.Medicine;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MedicineMapper extends AbstractMapper<MedicineDTO, Medicine> {
    MedicineMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(MedicineMapper.class);    
}
