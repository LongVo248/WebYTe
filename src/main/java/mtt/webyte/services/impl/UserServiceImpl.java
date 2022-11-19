package mtt.webyte.services.impl;

import mtt.webyte.dto.UserDTO;
import mtt.webyte.enums.RoleType;
import mtt.webyte.mapper.UserMapper;
import mtt.webyte.mapper.helper.CycleAvoidingMappingContext;
import mtt.webyte.model.User;
import mtt.webyte.repository.UserRepository;
import mtt.webyte.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public class UserServiceImpl extends AbstractServiceImpl<UserRepository, UserMapper, UserDTO, User> implements UserService {
    @Value("${security.password.expired.days}")
    private long PWD_EXPIRED_DAY;

    @Value("${security.registration.expired.days}")
    private long REG_EXPIRED_DAY;

    @Autowired(required = true)
    BCryptPasswordEncoder endcoder;


    @Override
    public boolean isEmailExist(String email) {
        return this.findByEmail(email) != null;
    }

    @Override
    public UserDTO findUserByUserId(Long userId) {
        return getMapper()
                .toDto(repository
                        .findByUserId(userId), new CycleAvoidingMappingContext());
    }

    @Override
    public UserDTO findUserByUserIdAndUpdatedDate(Long userId, Date updatedDate) {
        return null;
    }

    @Override
    public UserDTO findByEmail(String email) {
        return getMapper().toDto(getRepository().findByEmail(email),
                new CycleAvoidingMappingContext());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Long getUserIdByEmail(String email) {
        return getRepository().getUserIdByEmail(email);
    }

    @Override
    public UserDTO findByUserId(Long userId) {
        return getMapper().toDto(getRepository().findByUserId(userId), new CycleAvoidingMappingContext());
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO, int registrationType) {
        if (this.isEmailExist(userDTO.getEmail())) {
            return null;
        }
        UserDTO newUser = new UserDTO();
        newUser.setEmail(userDTO.getEmail());
        newUser.setPwd(endcoder.encode(userDTO.getPwd()));
        newUser.setUserFName(userDTO.getUserFName());
        newUser.setUserLName(userDTO.getUserLName());
        newUser.setPhoneNum(userDTO.getPhoneNum());
        newUser.setAddress(userDTO.getAddress());
        newUser.setImage(userDTO.getImage());
        newUser.setRoleType(RoleType.create(registrationType));
        newUser.setCreatedDate(new Date());
        return this.save(newUser);
    }
}
