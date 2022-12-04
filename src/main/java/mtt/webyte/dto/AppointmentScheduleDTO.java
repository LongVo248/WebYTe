package mtt.webyte.dto;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AppointmentScheduleDTO extends AbstractDTO implements Serializable {
    private Long appointmentId;
    private String appointmentDate;
    private String appointmentTime;
    private String appointmentStatus;
    private String appointmentNumber;
    private String appointmentType;
    private Long userId;
    private Long departmentId;
}
