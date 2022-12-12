
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
public class UpdateUserRequest extends AbstractDTO implements Serializable {
    private Long userId;
    private String userLName;
    private String userFName;
    private String phoneNum;
    private String address;
    private Date birthDate;
}
