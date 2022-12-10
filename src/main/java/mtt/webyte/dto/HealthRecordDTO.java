package mtt.webyte.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HealthRecordDTO extends AbstractDTO implements Serializable {
    private Long recordId;
    private Long prescriptionId;
    private Long appointmentScheduleId;
    private String description;
}
