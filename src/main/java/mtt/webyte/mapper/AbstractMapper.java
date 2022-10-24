package mtt.webyte.mapper;

import mtt.webyte.dto.AbstractNonAuditDTO;
import mtt.webyte.mapper.helper.CycleAvoidingMappingContext;
import mtt.webyte.model.AbstractEntity;
import org.mapstruct.Context;

public interface AbstractMapper <D extends AbstractNonAuditDTO, E extends AbstractEntity>{
    E toEntity(D dto, @Context CycleAvoidingMappingContext context);
    D toDto(E entity, @Context CycleAvoidingMappingContext context);
}
