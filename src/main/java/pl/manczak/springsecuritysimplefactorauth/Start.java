package pl.manczak.springsecuritysimplefactorauth;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.manczak.springsecuritysimplefactorauth.model.AppUser;
import pl.manczak.springsecuritysimplefactorauth.repo.AppUserRepo;

@Configuration
public class Start {


    // w klasie start wstrzykujemy poprzez konstruktor repozytorium i enkoder, następnie tworzymy urzytkownika, który ma zahaszowane hasło i póżniej jest zapisywany do bazy danych
    private AppUserRepo appUserRepo;

    public Start(AppUserRepo appUserRepo, PasswordEncoder passwordEncoder){
        this.appUserRepo=appUserRepo;

        AppUser appUserJanusz=new AppUser();
        appUserJanusz.setUsername("Janusz");
        appUserJanusz.setPassword(passwordEncoder.encode("Janusz123"));
        appUserJanusz.setRole("ROLE_ADMIN");
        appUserJanusz.setEnable(true);


        AppUser appUserBogdan=new AppUser();
        appUserBogdan.setUsername("Bogdan");
        appUserBogdan.setPassword(passwordEncoder.encode("Bogdan123"));
        appUserBogdan.setRole("ROLE_USER");
        appUserBogdan.setEnable(true);

        appUserRepo.save(appUserJanusz);
        appUserRepo.save(appUserBogdan);


    }
}
