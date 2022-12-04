package mtt.webyte.mapper;

import mtt.webyte.dto.PriceOfMedicalDTO;
import mtt.webyte.dto.QuestionDTO;
import mtt.webyte.model.PriceOfMedical;
import mtt.webyte.model.Question;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionMapper extends AbstractMapper<QuestionDTO, Question> {
    QuestionMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(QuestionMapper.class);
}
