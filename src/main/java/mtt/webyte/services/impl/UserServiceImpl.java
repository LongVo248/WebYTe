package mtt.webyte.services.impl;

import mtt.webyte.config.jwt.JwtUtil;
import mtt.webyte.dto.AuthenticationDTO;
import mtt.webyte.dto.ChangePasswordRequest;
import mtt.webyte.dto.ForgetPassword;
import mtt.webyte.dto.UpdateImageRequest;
import mtt.webyte.dto.UpdateUserRequest;
import mtt.webyte.dto.UserDTO;
import mtt.webyte.enums.RoleType;
import mtt.webyte.mapper.UserMapper;
import mtt.webyte.mapper.helper.CycleAvoidingMappingContext;
import mtt.webyte.model.Department;
import mtt.webyte.model.User;
import mtt.webyte.repository.UserRepository;
import mtt.webyte.services.SendMailService;
import mtt.webyte.services.UserService;
import mtt.webyte.util.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserServiceImpl extends AbstractServiceImpl<UserRepository, UserMapper, UserDTO, User> implements UserService {
    @Value("${security.password.expired.days}")
    private long PWD_EXPIRED_DAY;

    @Value("${security.registration.expired.days}")
    private long REG_EXPIRED_DAY;

    private static final UserMapper userMapper = UserMapper.INSTANCE;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder endcoder;

    @Autowired
    SendMailService sendMailService;

    @Autowired
    MailService mailService;

    @Override
    public boolean isEmailExist(String email) {
        return this.findByEmail(email) != null;
    }

    @Override
    public UserDTO findUserByUserId(Long userId) {
        return null;
    }

    @Override
    public UserDTO findUserByUserIdAndUpdatedDate(Long userId, Date updatedDate) {
        return null;
    }

    @Override
    public UserDTO findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return userMapper.toDto(user, new CycleAvoidingMappingContext());
    }
    
    public UserDTO updateImage(UpdateImageRequest request) {
	User user = userRepository.findByUserId(request.getId());
	user.setImage(request.getUrl());
	User updatedUser = userRepository.save(user);
	return userMapper.toDto(updatedUser, getCycleAvoidingMappingContext());
    }

    @Override
    public Long getUserIdByEmail(String email) {
        return null;
    }

    @Override
    public UserDTO findByUserId(Long userId) {
        return null;
    }

	public UserDTO forgetPassword(ForgetPassword dto) {
		User user = userRepository.findByEmail(dto.getEmail());
		String password = genRandomPassword(6);
		user.setPwd(endcoder.encode(password));
		userRepository.save(user);
		mailService.sendSimpleMessage(dto.getEmail(),"reset password", password);
		UserDTO userDTO = userMapper.toDto(user, getCycleAvoidingMappingContext());
		userDTO.setPwd("");
		return userDTO;
	}

	public String genRandomPassword(int n) {
		String AlphaNumericString = "0123456789";
StringBuilder sb = new StringBuilder(n);

for (int i = 0; i < n; i++) {

int index = (int)(AlphaNumericString.length() * Math.random());

sb.append(AlphaNumericString.charAt(index));
}

return sb.toString();
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

    @Override
    public Map<String, Object> login(AuthenticationDTO accountDTO) {
        Map<String, Object> paren = new HashMap<String, Object>();
        String role = "";
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(accountDTO.getUsername(), accountDTO.getPassword()));
            User userDTO = userRepository.findByUsername(accountDTO.getUsername());
            switch (userDTO.getRole().getType()) {
                case 1:
                    role = "user";
                    break;
                case 3:
                    role = "admin";
                    break;
                case 2:
                    role = "doctor";
                    break;
                default:
                    role = "";
                    break;
            }
            paren.put("username", userDTO.getUsername());
            paren.put("role", role);
            paren.put("id", userDTO.getUserId());
            paren.put("message", "Đăng nhập thành công");
            paren.put("token", jwtUtil.generateToken(accountDTO.getUsername()));
            return paren;
        } catch (Exception ex) {
            System.out.println(ex);
            paren.put("username", null);
            paren.put("role", null);
            paren.put("message", "Đăng nhập thất bại");
            paren.put("token", null);
            return paren;
        }
    }

    @Override
    public User registerUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO, new CycleAvoidingMappingContext());
        user.setRole(RoleType.create(1));
        user.setPwd(endcoder.encode(userDTO.getPwd()));
        user.setCreatedDate(new Date());
        user.setCreatedBy(userDTO.getEmail());
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllListAccount() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public UserDTO updateAccount(UserDTO userDTO) {
        User user1 = userRepository.findByEmail(userDTO.getEmail());
	Long id = user1.getUserId();
	System.out.println(user1);
	System.out.println(userDTO);
	user1.setUserId(id);
        if (user1.getRole().getType() == 1) {
            user1.setRole(RoleType.create(1));
        } else if (user1.getRole().getType() == 2) {
            user1.setRole(RoleType.create(2));
        } else if (user1.getRole().getType() == 3) {
            user1.setRole(RoleType.create(3));
        }
        user1.setModifiedDate(new Date());
        user1.setModifiedBy(userDTO.getEmail());
	userRepository.save(user1);
        return userMapper.toDto(userRepository.save(user1), new CycleAvoidingMappingContext());
    }

    public UserDTO updateAccount(UpdateUserRequest userDTO) {
        User user1 = userRepository.findByUserId(userDTO.getUserId());
	userMapper.update(user1, userDTO);
        user1.setModifiedDate(new Date());
	userRepository.save(user1);
        return userMapper.toDto(userRepository.save(user1), new CycleAvoidingMappingContext());
    }

    @Override
    public UserDTO deleteAccount(Long id) {
        return null;
    }

    @Override
    public UserDTO changePassword(Long id, String password) {
        if (checkPassword(id, password)) {
            return null;
        }
        User user = userRepository.findById(id).get();
        user.setPwd(endcoder.encode(password));
        user.setModifiedDate(new Date());
        user.setModifiedBy(user.getEmail());
        return userMapper.toDto(userRepository.save(user), new CycleAvoidingMappingContext());
    }

    @Override
    public UserDTO getAccountById(Long id) {
        User user = userRepository.findById(id).get();
        UserDTO userDTO = userMapper.toDto(user, new CycleAvoidingMappingContext());
	userDTO.setPwd("");
        if (user.getRole().getType() == 1) {
            userDTO.setRoleType(RoleType.create(1));
        } else if (user.getRole().getType() == 2) {
            userDTO.setRoleType(RoleType.create(2));
        } else if (user.getRole().getType() == 3) {
            userDTO.setRoleType(RoleType.create(3));
        }
        return userDTO;
    }

    @Override
    @Transactional
    public boolean forgotPassword(UserDTO userDTO) {
        User user = userRepository.findByEmail(userDTO.getEmail());
        userDTO.setPwd(DataUtils.generateTempPwd(8));
        sendMailService.forgotPassword(userDTO, userDTO.getPwd());
        userDTO.setPwd(BCrypt.hashpw(userDTO.getPwd(), BCrypt.gensalt(12)));
        User user1 = userMapper.toEntity(userDTO, new CycleAvoidingMappingContext());
        if (user.getRole().getType() == 1) {
            user1.setRole(RoleType.create(1));
        } else if (user.getRole().getType() == 2) {
            user1.setRole(RoleType.create(2));
        } else if (user.getRole().getType() == 3) {
            user1.setRole(RoleType.create(3));
        }
        user1.setModifiedDate(new Date());
        user1.setModifiedBy(userDTO.getEmail());
        return Objects.isNull(userRepository.save(user1)) ? false : true;
    }

    @Override
    public boolean checkPassword(Long id, String password) {
        UserDTO userDTO = this.getAccountById(id);
        userDTO.setPwd(password);
        User user = userRepository.findByEmail(userDTO.getEmail());
        return BCrypt.checkpw(userDTO.getPwd(), user.getPwd());
    }

    public boolean changePassword(ChangePasswordRequest request) {
	User user = userRepository.findByUserId(request.getId());
	if (BCrypt.checkpw(request.getOldPassword(), user.getPwd())) {
		String newPass = endcoder.encode(request.getNewPassword());
		user.setPwd(newPass);
		userRepository.save(user);
	}
	return true;
    }

    public List<UserDTO> findAllPatient(){
	    Set<User> patients = userRepository.findAllByRole(RoleType.ROLE_USER);
	    List<UserDTO> patientDTOs = new ArrayList<>();

	    for (User patient: patients) {
		    patient.setPwd("");
			patientDTOs.add(userMapper.toDto(patient, getCycleAvoidingMappingContext()));
	    }

	    return patientDTOs;
    }

    @Override
    public UserDTO findByUsername(String username) {
        return userMapper.toDto(userRepository.findByUsername(username), new CycleAvoidingMappingContext());
    }

    @Override
    public UserDTO save(UserDTO dto) {
        //return userMapper.toDto(userRepository.save(userMapper.toEntity(dto, new CycleAvoidingMappingContext())), new CycleAvoidingMappingContext());
//        return userRepository.save(userMapper.toEntity(dto, new CycleAvoidingMappingContext()));
        return null;
    }

    @Override
    public User save(User entity) {
        return null;
    }

    @Override
    public UserDTO saveAndFlush(UserDTO dto) {
        return null;
    }

    @Override
    public User saveAndFlush(User entity) {
        return null;
    }

    @Override
    public UserDTO save() {
        return null;
    }

    @Override
    public List<UserDTO> save(List<UserDTO> dtos) {
        return null;
    }

    @Override
    public UserDTO findById(long id) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void delete(UserDTO dto) {

    }

    @Override
    public List<UserDTO> findAll() {
        return null;
    }

    @Override
    public UserDTO findById(Object id) {
        return null;
    }

    @Override
    public User findEntityById(Object id) {
        return null;
    }

    @Override
    public void delete(Object dto) {

    }

    @Override
    public Pageable getPageable(Integer page, Integer size, boolean sortASC, String by) {
        return null;
    }

    @Override
    public Pageable getPageable(Integer page, Integer size) {
        return null;
    }
}
