package mtt.webyte.services;

import mtt.webyte.dto.UserDTO;
import mtt.webyte.model.User;

import java.util.Date;

public interface UserService extends AbstractService<UserDTO, User> {
    boolean isEmailExist(String email);

    UserDTO findUserByUserId(Long userId);

    UserDTO findUserByUserIdAndUpdatedDate(Long userId, Date updatedDate);

    UserDTO findByEmail(String email);

    Long getUserIdByEmail(String email);

    UserDTO findByUserId(Long userId);

    UserDTO saveUser(UserDTO userDTO, int registrationType);

    //Page<UserRoleDTO> fetchingUserListMerchantPortal(UserDTO userDTO, Integer roleId, Integer notificationId, Long buUnitId, Pageable pageable);
}
