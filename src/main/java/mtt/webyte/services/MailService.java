package mtt.webyte.services;

import mtt.webyte.dto.DataMailDTO;
import org.springframework.scheduling.annotation.Async;

import javax.mail.MessagingException;

public interface MailService {

    @Async
    void sendHtmlMail(DataMailDTO dataMail, String templateName) throws MessagingException;
}
