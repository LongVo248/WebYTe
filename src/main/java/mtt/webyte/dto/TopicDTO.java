package mtt.webyte.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TopicDTO extends AbstractDTO implements Serializable {
    private Long topicId;
    private String topicName;
    private String topicContent;
}
