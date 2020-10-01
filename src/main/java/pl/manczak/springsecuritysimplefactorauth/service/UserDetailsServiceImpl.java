package pl.manczak.springsecuritysimplefactorauth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.manczak.springsecuritysimplefactorauth.repo.AppUserRepo;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    private AppUserRepo appUserRepo;

    public UserDetailsServiceImpl(AppUserRepo appUserRepo) {
        this.appUserRepo = appUserRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        //to do trow if not exist
        // tu zapewniamy obsługę co zrobić w przypdaku gdy nie znajdzie takiego elementu
        // w App User Repo robimy wtedy Optionala

        return appUserRepo.findByUsername(s).get();
    }
}
