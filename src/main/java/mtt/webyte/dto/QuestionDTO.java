package mtt.webyte.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class QuestionDTO extends AbstractDTO implements Serializable {
    private Long questionId;
    private String questionContent;
    private Long topicId;
    private Long userId;
    private UserDTO userDTO;
    private Set<AnswerDTO> answerDTOs;
}
