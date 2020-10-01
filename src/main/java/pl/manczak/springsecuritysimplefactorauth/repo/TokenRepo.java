package pl.manczak.springsecuritysimplefactorauth.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.manczak.springsecuritysimplefactorauth.model.Token;


@Repository
public interface TokenRepo extends JpaRepository<Token, Long> {

    Token findByValue(String value);

}
