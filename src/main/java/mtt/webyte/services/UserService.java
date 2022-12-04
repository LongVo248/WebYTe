package mtt.webyte.services;

import mtt.webyte.dto.AuthenticationDTO;
import mtt.webyte.dto.UserDTO;
import mtt.webyte.model.User;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserService extends AbstractService<UserDTO, User> {
    boolean isEmailExist(String email);

    UserDTO findUserByUserId(Long userId);

    UserDTO findUserByUserIdAndUpdatedDate(Long userId, Date updatedDate);

    UserDTO findByEmail(String email);

    Long getUserIdByEmail(String email);

    UserDTO findByUserId(Long userId);

    UserDTO saveUser(UserDTO userDTO, int registrationType);

    UserDTO findByUsername(String username);

    Map<String,Object> login(AuthenticationDTO accountDTO);

    User registerUser(UserDTO userDTO);

    List<User> getAllListAccount();

    UserDTO updateAccount(UserDTO userDTO);

    UserDTO deleteAccount(Long id);

    UserDTO changePassword(Long id,String password);

    UserDTO getAccountById(Long id);

    boolean forgotPassword(UserDTO userDTO);

    boolean checkPassword(Long id, String password);
}
