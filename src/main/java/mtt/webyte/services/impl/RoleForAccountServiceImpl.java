package mtt.webyte.services.impl;

import mtt.webyte.dto.RoleDTO;
import mtt.webyte.services.RoleForAccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleForAccountServiceImpl implements RoleForAccountService {
    @Override
    public List<RoleDTO> getRoleForAccount(String username) {
        return null;
    }
}
