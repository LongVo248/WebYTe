package mtt.webyte.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AnswerDTO extends AbstractDTO implements Serializable {
    private Long answerId;
    private String answerContent;
    private Long questionId;
    private Long userId;
    private UserDTO userDTO;
}
