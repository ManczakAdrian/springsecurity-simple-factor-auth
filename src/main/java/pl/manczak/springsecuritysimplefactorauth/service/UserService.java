package pl.manczak.springsecuritysimplefactorauth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.manczak.springsecuritysimplefactorauth.model.AppUser;
import pl.manczak.springsecuritysimplefactorauth.model.Token;
import pl.manczak.springsecuritysimplefactorauth.repo.AppUserRepo;
import pl.manczak.springsecuritysimplefactorauth.repo.TokenRepo;

import javax.mail.MessagingException;
import javax.sql.RowSet;
import java.util.UUID;


@Service
public class UserService {

    private TokenRepo tokenRepo;


    private MailService mailService;
    private AppUserRepo appUserRepo;
    private PasswordEncoder passwordEncoder;


    public UserService(AppUserRepo appUserRepo, PasswordEncoder passwordEncoder, TokenRepo tokenRepo, MailService mailService, RowSet appUser, String url) {
        this.appUserRepo = appUserRepo;
        this.passwordEncoder = passwordEncoder;
        this.tokenRepo = tokenRepo;
        this.mailService=mailService;


    }

    public void addUser(AppUser appUser) {
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUser.setRole("ROLE_USER");
        appUserRepo.save(appUser);
        sendToken(appUser);
    }

    private void sendToken(AppUser appUser) {
        String tokenValue = UUID.randomUUID().toString();
        Token token = new Token();
        token.setValue(tokenValue);
        token.setAppUser(appUser);
        tokenRepo.save(token);
        String url = "http://localhost:8080/token?value=" + tokenValue;

        try {
            mailService.sendMail(appUser.getUsername(),"Potwierdzaj to!", url, false);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}