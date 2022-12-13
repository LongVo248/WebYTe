package mtt.webyte.dto;

import lombok.*;
import mtt.webyte.model.User;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AppointmentScheduleDTO extends AbstractDTO implements Serializable {
    private Long appointmentId;
    private Date appointmentDate;
    private String appointmentTime;
    private String appointmentStatus;
    private String appointmentNumber;
    private String appointmentType;
    private Long userId;
    private Long doctorId;
    private UserDTO doctorDTO; 
    private UserDTO patientDTO;

    private Long price;
}
