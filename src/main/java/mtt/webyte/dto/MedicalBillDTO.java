package mtt.webyte.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MedicalBillDTO extends AbstractDTO implements Serializable {
    private Long billId;
    private Long totalPrice;
    private Long appointmentScheduleId;
}
