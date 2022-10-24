package mtt.webyte.services.impl;

import mtt.webyte.config.jwt.JwtTokenProvider;
import mtt.webyte.dto.AccountDTO;
import mtt.webyte.ac.ERole;
import mtt.webyte.dto.PatientDTO;
import mtt.webyte.model.Account;
import mtt.webyte.model.Role;
import mtt.webyte.repository.AccountRepository;
import mtt.webyte.repository.RoleRepository;
import mtt.webyte.services.AccountService;
import mtt.webyte.services.PatientService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.transaction.SystemException;
import java.util.*;

@Service
public class AccountImpl implements AccountService {
    private static final String BL_ID = "BL-ACC-IMPl";
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(AccountImpl.class);

    @Autowired
    public AccountRepository accountRepository;
    @Autowired
    public RoleRepository roleRepository;
    @Autowired
    public AuthenticationManager authenticationManager;
    @Autowired
    public JwtTokenProvider jwtTokenProvider;
    @Autowired
    public PatientService patientService;
    @Autowired
    public PasswordEncoder passwordEncoder;


    @Override
    public Map<String, Object> login(AccountDTO accountDTO) {
        Map<String, Object> paren = new HashMap<>();
        final String[] role = {""};
        try {
            // step 1: check input parameter
            checkInputParameter(accountDTO);

            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(accountDTO.getUserName(), accountDTO.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            Optional<Account> account = accountRepository.findById(accountDTO.getUserName());
            String jwt = jwtTokenProvider.generateToken(authentication);
            if (account.isPresent()) {
                account.get().getRoles().forEach(role1 -> {
                    role[0] = role1.getRoleName();
                });
                for (Role r : account.get().getRoles()) {
                    if (r.getRoleId() == 1) {
                        role[0] = ERole.USER;
                    } else if (r.getRoleId() == 2) {
                        role[0] = ERole.ADMIN;
                    } else if (r.getRoleId() == 3) {
                        role[0] = ERole.DOCTOR;
                    } else {
                        role[0] = "";
                    }
                }
            }
            paren.put("username", accountDTO.getUserName());
            paren.put("role", role[0]);
            paren.put("message", "Đăng nhập thành công");
            paren.put("token", jwt);
            logger.info(BL_ID + " - Login successfully");
            logger.info(BL_ID + " - Parent: " + paren);
            return paren;
        } catch (Exception e) {
            e.printStackTrace();
            paren.put("username", null);
            paren.put("role", null);
            paren.put("message", "Đăng nhập thất bại");
            paren.put("token", null);
            logger.error(BL_ID + " - Login failed");
            return paren;
        }
    }

    private void checkInputParameter(AccountDTO accountDTO) {
        if (accountDTO.getUserName() == null || accountDTO.getUserName().isEmpty()) {
            logger.error(BL_ID + " - Username is null or empty");
            throw new RuntimeException("Username is null");
        }
        if (accountDTO.getPassword() == null || accountDTO.getPassword().isEmpty()) {
            logger.error(BL_ID + " - Password is null or empty");
            throw new RuntimeException("Password is null");
        }
    }

    public List<Account> findAllAccount() {
        return accountRepository.findAll();
    }


    public Account findAccountByUserName(String userName) {
        try {
            return accountRepository.findById(userName).get();
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Account register(List<Map> account) {
        // step 1 check input parameter
        checkInputRegister(account);

        // step 2 check exist account
        checkExistAccount(account);

        // step 3 create account
        try {
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setUserName(account.get(0).get("userName").toString());
            accountDTO.setPassword(account.get(0).get("password").toString());
            accountDTO.setRoleId(account.get(0).get("roleId"));
            PatientDTO patientDTO = new PatientDTO(account.get(1));
            patientDTO.email = account.get(1).get("email").toString();
            patientDTO.phone = account.get(1).get("phone").toString();
            patientDTO.firstName = account.get(1).get("firstName").toString();
            patientDTO.lastName = account.get(1).get("lastName").toString();
            Account account1 = insertAccount(accountDTO);
            patientService.insertPatientSignup(account1.getUserName() ,patientDTO);
            return account1;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(BL_ID + " - Register failed");
        }
        return null;
    }

    private void checkExistAccount(List<Map> account){
        if (accountRepository.findById(account.get(0).get("userName").toString()).isPresent()) {
            logger.error(BL_ID + " - Account is exist");
            throw new RuntimeException("Account is exist");
        }
    }

    private void checkInputRegister(List<Map> account) {
        if (account == null || account.isEmpty()) {
            logger.error(BL_ID + " - Account is null or empty");
            throw new RuntimeException("Account is null");
        }
        if (account.get(0) == null || account.get(0).isEmpty()) {
            logger.error(BL_ID + " - AccountDTO is null or empty");
            throw new RuntimeException("AccountDTO is null");
        }
        if (account.get(1) == null || account.get(1).isEmpty()) {
            logger.error(BL_ID + " - PatientDTO is null or empty");
            throw new RuntimeException("PatientDTO is null");
        }
    }


    public Account insertAccount(AccountDTO accountDTO) {
        try {
            Account account = new Account();
            account.setUserName(accountDTO.getUserName());
            account.setPassword(passwordEncoder.encode(accountDTO.getPassword()));
            Optional<Role> role = roleRepository.findById(accountDTO.getRoleId());
            account.setRoles(Collections.singleton(role.get()));
            account.setCreatedBy(account.getUserName());
            account.setCreatedDate(new Date());
            account.setModifiedDate(new Date());
            account.setModifiedBy(account.getUserName());
            accountRepository.save(account);
            logger.info(BL_ID + " - Insert account successfully");
            return account;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(BL_ID + " - Insert account failed");
            throw new RuntimeException("Insert account failed");
        }
    }


    public Account updateAccount(Account account) {
        try {
            return accountRepository.save(account);
        } catch (Exception e) {
            return null;
        }
    }

    public Account deleteAccount(Account account) {
        try {
            accountRepository.delete(account);
            return account;
        } catch (Exception e) {
            return null;
        }
    }
}
