package mtt.webyte.controller;

import mtt.webyte.dto.AuthenticationDTO;
import mtt.webyte.dto.UserDTO;
import mtt.webyte.model.User;
import mtt.webyte.services.UserService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.SystemException;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/webyte/account")
public class AuthenticationRestController {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(AuthenticationRestController.class);
    private static final String BLID = "CTL-ACC";

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    @SuppressWarnings("unchecked")
    public ResponseEntity<?> login(@Valid @RequestBody AuthenticationDTO authDto,
                                   HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.info(request.getRequestURL().toString());
        logger.info("authDto", authDto);
        try {
            Map<String, Object> account = userService.login(authDto);
            return ResponseEntity.ok(account);
        } catch (BadCredentialsException e) {
            logger.error(request.getRequestURL().toString(), "login", e);
            throw new SystemException("Incorrect username or password");
        }
    }

    @PostMapping("/signupUser")
    public ResponseEntity<?> registerAccount(@RequestBody UserDTO userDTO,
                                             HttpServletRequest request, HttpServletResponse response) {
        logger.info(request.getRequestURL().toString());
        logger.debug("userDTO", userDTO);
        try {
            logger.info("Successly registered user");
            return ResponseEntity.ok(userService.registerUser(userDTO));
        } catch (Exception e) {
            logger.error("registerAccount", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get-all-account")
    public List<User> getAllAccount() {
        try {
            logger.info("Successly get all account");
            List<User> accountList = userService.getAllListAccount();
            return accountList;
        } catch (Exception e) {
            logger.error("getAllAccount", e);
            return null;
        }
    }

    @PutMapping("/update-account")
    public ResponseEntity<?> updateAccount(@RequestBody UserDTO userDTO,
                                           HttpServletRequest request, HttpServletResponse response) {
        logger.info(request.getRequestURL().toString());
        logger.debug("userDTO", userDTO);
        try {
            UserDTO account = userService.updateAccount(userDTO);
            logger.info("Successly updated user");
            return ResponseEntity.ok(account);
        } catch (Exception e) {
            logger.error("updateAccount", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get-account-by-id/{id}")
    public ResponseEntity<?> getAccountById(@PathVariable("id") Long id,
                                            HttpServletRequest request, HttpServletResponse response) {
        logger.info(request.getRequestURL().toString());
        logger.debug("userId", id);
        try {
            UserDTO account = userService.getAccountById(id);
            logger.info("Successly get user");
            return ResponseEntity.ok(account);
        } catch (Exception e) {
            logger.error("getAccountById", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/forgot-password/{email}")
    public ResponseEntity<?> forgotPassword(@PathVariable("email") String email,
                                            HttpServletRequest request, HttpServletResponse response) {
        logger.info(request.getRequestURL().toString());
        logger.debug("email", email);
        try {
            UserDTO account = userService.findByEmail(email);
            logger.info("Successly send mail forgot password");
            return new ResponseEntity<>(userService.forgotPassword(account), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("forgotPassword", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/check-password/{id}")
    public ResponseEntity<?> checkPassword(@PathVariable("id") Long id,
                                           @RequestBody String password,
                                           HttpServletRequest request, HttpServletResponse response) {
        logger.info(request.getRequestURL().toString());
        logger.debug("id", id);

        try {
            logger.info("Successly check password");
            return new ResponseEntity<>(userService.checkPassword(id, password), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("checkPassword", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/change-password/{id}")
    public ResponseEntity<?> changePassword(@PathVariable("id") Long id,
                                            @RequestBody String password,
                                            HttpServletRequest request, HttpServletResponse response) {
        logger.info(request.getRequestURL().toString());
        logger.debug("id", id);

        try {
            logger.info("Successly change password");
            return new ResponseEntity<>(!Objects.isNull(userService.changePassword(id, password)), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("changePassword", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
