package mtt.webyte.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.*;
import mtt.webyte.enums.RoleType;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DoctorDTO extends AbstractDTO implements Serializable { 
    private Long userId;
    private String userLName;
    private String userFName;
    private String username;
    private String email;
    private String phoneNum;
    private String address;
    private Date birthDate;
    private String image;
    private String doctorName;
    private List<Long> departmentId;
}
