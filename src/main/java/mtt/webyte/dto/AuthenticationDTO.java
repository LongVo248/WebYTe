package mtt.webyte.dto;

import lombok.Getter;
import lombok.Setter;
import mtt.webyte.enums.RoleType;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AuthenticationDTO {
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private RoleType roleType;

    @Override
    public String toString() {
        return "AuthenticationDTO [username=" + username + ", password=" + password + ", roleType="
                + roleType + "]";
    }
}
