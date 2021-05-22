package ca.marcinkoziel.portfolio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Date;

@Service("mailService")
public class MailServiceImpl implements MailService {

        @Autowired
        private JavaMailSender javaMailSender;

        @Override
        public void send(String fromAddress, String toAddress, String subject, String reason, String message) throws Exception{

                MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
                mimeMessageHelper.setFrom(fromAddress);
                mimeMessageHelper.setTo(toAddress);
                mimeMessageHelper.setSubject(subject);
                mimeMessageHelper.setText(message, true);
                mimeMessageHelper.setSentDate(new Date());
                javaMailSender.send(mimeMessage);

        }

}
