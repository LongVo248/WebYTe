package mtt.webyte.dto;

import lombok.*;
import mtt.webyte.model.Role;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AccountDTO extends AbstractDTO {
    private String userName;
    private String password;
    private Integer roleId;

    public AccountDTO(Map<String, Object> map) {
        this.userName = (String) map.get("userName");
        this.password = (String) map.get("password");
        this.roleId = (Integer) map.get("roleId");
    }

    public void setRoleId(Object roleId) {
        if (roleId instanceof Role) {
            this.roleId = ((Role) roleId).getRoleId();
        } else {
            this.roleId = (Integer) roleId;
        }
    }
}
