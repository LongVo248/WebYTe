package mtt.webyte.controller;

import mtt.webyte.dto.AuthenticationDTO;
import mtt.webyte.dto.UserDTO;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.transaction.SystemException;
import javax.validation.Valid;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/webyte/account")
public class AuthenticationRestController {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(AuthenticationRestController.class);
    private static final String BLID = "CTL-ACC";

    @Autowired
    AuthenticationManager authenticationManager;


    @PostMapping("/login")
    @SuppressWarnings("unchecked")
    public ResponseEntity<?> login(@Valid @RequestBody AuthenticationDTO authDto) throws SystemException {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//            UserDTO userDTO = new UserDTO(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
        } catch (BadCredentialsException e) {
            throw new SystemException("Incorrect username or password");
        }
        return ResponseEntity.ok("Login success");
    }


//    @PostMapping("/register")
//    public Account register(@Valid @RequestBody List<Map> registerRequest) {
//        try {
//            logger.info(BLID + " - Registering new account");
//            return accountService.register(registerRequest);
//        } catch (Exception e) {
//            logger.error(BLID + " - Error while registering new account");
//            e.printStackTrace();
//            throw new BadCredentialsException("Error while registering new account");
//        }
//    }
//
//
//    @PostMapping("/logout")
//    public void logout() {
//        logger.info("Logout");
//    }
//
//    @PostMapping("/add-account")
//    public void addAccount(@RequestBody Account account) {
//        try {
//            logger.info(BLID + " - Adding new account");
//            //accountService.insertAccount(account);
//        } catch (Exception e) {
//            logger.error(BLID + " - Error while adding new account");
//            e.printStackTrace();
//        }
//    }
//    @GetMapping("/get-all-account")
//    public List<Account> getAccountList(){
//        try {
//            List<Account> accountList = accountService.findAllAccount();
//            return accountList;
//        } catch (Exception e) {
//            logger.error(BLID + " - Error while getting account list");
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @GetMapping("/random")
//    public String randomStuff(){
//        return "JWT Hợp lệ mới có thể thấy được message này";
//    }
}
