package ca.marcinkoziel.portfolio.services;


public interface MailService {

    public void send(String fromAddress, String toAddress, String subject, String reason, String message) throws Exception;

}
