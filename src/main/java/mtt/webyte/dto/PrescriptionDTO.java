package mtt.webyte.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PrescriptionDTO extends AbstractDTO implements Serializable {
    private Long prescriptionId;
    private String prescriptionDate;
    private String prescriptionTime;
    private Long prescriptionAmount;
    private String prescriptionDosage;
    private String prescriptionDuration;
}
