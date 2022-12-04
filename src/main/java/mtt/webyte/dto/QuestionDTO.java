package mtt.webyte.dto;

import lombok.*;

import java.io.Serializable;

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
}
