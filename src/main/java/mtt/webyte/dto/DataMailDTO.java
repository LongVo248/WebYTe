package mtt.webyte.dto;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DataMailDTO {

    private String to;
    private String subject;
    private String content;
    private Map<String, Object> props;
}
