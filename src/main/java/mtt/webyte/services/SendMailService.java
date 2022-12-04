package mtt.webyte.services;

import mtt.webyte.dto.UserDTO;

public interface SendMailService {
    Boolean signup(String email, String token);

    Boolean create(String email, String password);

    void forgotPassword(UserDTO userDTO, String password);
}
