package mtt.webyte.mapper;

import mtt.webyte.dto.AccountDTO;
import mtt.webyte.mapper.helper.CycleAvoidingMappingContext;
import mtt.webyte.model.Account;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper extends AbstractMapper<AccountDTO, Account> {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);
    @Mappings({
        @Mapping(target = "password", ignore = true)
    })
    AccountDTO toDto(Account entity, @Context CycleAvoidingMappingContext context);

    @Mappings({
        @Mapping(target = "password", ignore = true),
        @Mapping(target = "createdDate", ignore = true),
        @Mapping(target = "modifiedDate", ignore = true)
    })
    Account toEntity(AccountDTO dto, @Context CycleAvoidingMappingContext context);
}
