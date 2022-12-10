package mtt.webyte.dto;

import lombok.*;
import mtt.webyte.enums.RoleType;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO extends AbstractDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long userId;
    private String userLName;
    private String userFName;
    private String username;
    private String pwd;
    private String email;
    private String phoneNum;
    private String address;
    private Date birthDate;
    private String image;
    private String doctorName;
    private RoleType roleType;
}
