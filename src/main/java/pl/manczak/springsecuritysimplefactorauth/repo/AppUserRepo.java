package pl.manczak.springsecuritysimplefactorauth.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.manczak.springsecuritysimplefactorauth.model.AppUser;

import java.util.Optional;


@Repository
public interface AppUserRepo extends JpaRepository<AppUser,Long> {
    Optional<AppUser> findByUsername(String username);


}



