package mtt.webyte.dto;

public class AccountDTO {
    public String userName;
    public String password;
    public Long roleId;

    public AccountDTO() {
    }

    public AccountDTO(Long roleId, String userName, String password) {
        this.roleId = roleId;
        this.userName = userName;
        this.password = password;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
