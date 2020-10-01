package pl.manczak.springsecuritysimplefactorauth.controller;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.manczak.springsecuritysimplefactorauth.model.Token;
import pl.manczak.springsecuritysimplefactorauth.repo.AppUserRepo;
import pl.manczak.springsecuritysimplefactorauth.repo.TokenRepo;
import pl.manczak.springsecuritysimplefactorauth.service.UserService;
import pl.manczak.springsecuritysimplefactorauth.model.AppUser;

import java.security.Principal;
import java.util.Collection;

@Controller
public class UserControler {


    private UserService userService;
    private AppUserRepo appUserRepo;
    private TokenRepo tokenRepo;

    public UserControler(UserService userService, AppUserRepo appUserRepo, TokenRepo tokenRepo) {
        this.userService = userService;
        this.appUserRepo = appUserRepo;
        this.tokenRepo = tokenRepo;
    }

    //for REST value returned
/*
    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello";
    }*/


    @GetMapping("/hello")
    public String hello(Principal principal, Model model) {
        model.addAttribute("name", principal.getName());

        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        model.addAttribute("authorities", authorities);

        return "hello";

    }

    @GetMapping("/sign-up")
    public String singup(Model model) {
        model.addAttribute("user", new AppUser());
        return "sing-up";

    }

    @PostMapping("/register")
    public String register(AppUser appUser) {
        userService.addUser(appUser);
        return "sing-up";

    }

    @PostMapping("/token")
    public String singup(@RequestParam String value) {
        Token byValue = tokenRepo.findByValue(value);
        AppUser appUser = byValue.getAppUser();

        appUser.setEnable(true);
        appUserRepo.save(appUser);

        return "hello";

    }
}
