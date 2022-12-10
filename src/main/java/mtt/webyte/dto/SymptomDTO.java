package mtt.webyte.dto;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SymptomDTO extends AbstractDTO implements Serializable {
    private Long symptomId;
    private String symptomName;
}
