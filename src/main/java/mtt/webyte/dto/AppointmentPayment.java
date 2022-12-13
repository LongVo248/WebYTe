package mtt.webyte.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AppointmentPayment {
    private Integer idappointmentSchedule;

    private int doctorid;

    private int patientid;

    private Date date;
    private Integer number;
    private String status;
    private Long price;
    private String time;
    private  String typeclinic;
}
