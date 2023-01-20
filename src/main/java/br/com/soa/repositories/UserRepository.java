package br.com.soa.repositories;

import br.com.soa.domain.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

    public Optional<User> findByUsuario(String username){
       return find("username = ?1", username).firstResultOptional();
    }
}
