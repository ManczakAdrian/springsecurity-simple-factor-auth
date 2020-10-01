package pl.manczak.springsecuritysimplefactorauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.manczak.springsecuritysimplefactorauth.service.UserDetailsServiceImpl;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    //adnotacja Bean pozwala na wstrzykiwanie instancji enkodera w innych klasach
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();

    }


    // dokonujemy wstrzyknięcia UserDetail Service poprzez konstruktor

    private UserDetailsService userDetailsService;

    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().disable();

        //Spring security dostarcza różnego rodzaju zabezpieczenia. Wśród nich jest zabezpieczenie które nas broni przed przypadkowym wysłaniem formularza CSRF - Cross sign request forein. Wyłaczamy to zabezpieczenie
    // wyłączamy też blokowanie nagłówków
        // robimy to po to żeby mieć dostęp do bazy h2

        http.authorizeRequests()
                .antMatchers("/hello").authenticated()
                .and()
                .formLogin().defaultSuccessUrl("/hello");

    }
}
