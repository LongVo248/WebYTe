package mtt.webyte.services;

import mtt.webyte.dto.RoleDTO;

import java.util.List;

public interface RoleForAccountService {
    List<RoleDTO> getRoleForAccount(String username);
}
