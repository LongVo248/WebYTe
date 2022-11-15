package mtt.webyte.controller;

import mtt.webyte.config.jwt.JwtTokenProvider;
import mtt.webyte.dto.AccountDTO;
import mtt.webyte.dto.AuthenticationResponseDTO;
import mtt.webyte.dto.LoginRequestDTO;
import mtt.webyte.model.Account;
import mtt.webyte.model.Role;
import mtt.webyte.services.AccountService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import javax.transaction.SystemException;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/webyte/account")
public class AccountController {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(AccountController.class);
    private static final String BLID = "CTL-ACC";

    @Autowired
    public AccountService accountService;

    @Autowired
    AuthenticationManager authenticationManager;


    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody AccountDTO accountDTO) throws Exception{
        try {
            logger.info("Login request for user: " + accountDTO.toString());
            Map<String, Object> map = accountService.login(accountDTO);
            if (map != null) {
                return map;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new SystemException();
        }

    }

    @PostMapping("/register")
    public Account register(@Valid @RequestBody List<Map> registerRequest) {
        try {
            logger.info(BLID + " - Registering new account");
            return accountService.register(registerRequest);
        } catch (Exception e) {
            logger.error(BLID + " - Error while registering new account");
            e.printStackTrace();
            throw new BadCredentialsException("Error while registering new account");
        }
    }


    @PostMapping("/logout")
    public void logout() {
        logger.info("Logout");
    }

    @PostMapping("/add-account")
    public void addAccount(@RequestBody Account account) {
        try {
            logger.info(BLID + " - Adding new account");
            //accountService.insertAccount(account);
        } catch (Exception e) {
            logger.error(BLID + " - Error while adding new account");
            e.printStackTrace();
        }
    }
    @GetMapping("/get-all-account")
    public List<Account> getAccountList(){
        try {
            List<Account> accountList = accountService.findAllAccount();
            return accountList;
        } catch (Exception e) {
            logger.error(BLID + " - Error while getting account list");
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/random")
    public String randomStuff(){
        return "JWT Hợp lệ mới có thể thấy được message này";
    }
}
