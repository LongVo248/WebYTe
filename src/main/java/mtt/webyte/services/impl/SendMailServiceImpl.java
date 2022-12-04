package mtt.webyte.services.impl;

import mtt.webyte.dto.DataMailDTO;
import mtt.webyte.dto.UserDTO;
import mtt.webyte.services.MailService;
import mtt.webyte.services.SendMailService;
import mtt.webyte.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;

@Service
public class SendMailServiceImpl implements SendMailService {

    @Autowired
    MailService mailService;

    @Override
    public Boolean signup(String email, String token) {
        return null;
    }

    @Override
    public Boolean create(String email, String password) {
        return null;
    }

    @Override
    public void forgotPassword(UserDTO sdi, String password) {
        try {
            DataMailDTO dataMail = new DataMailDTO();

            dataMail.setTo(sdi.getEmail());
            dataMail.setSubject(Const.SEND_MAIL_SUBJECT.CLIENT_FORGET_PASSWORD);

            Map<String, Object> props = new HashMap<>();
            props.put("Fullname", sdi.getUserLName() + " " + sdi.getUserFName());
            props.put("Password", password);
            dataMail.setProps(props);

            mailService.sendHtmlMail(dataMail, Const.TEMPLATE_FILE_NAME.CLIENT_FORGET_PASSWORD);
        } catch (MessagingException exp) {
            exp.printStackTrace();
        }
    }
}
