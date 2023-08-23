package br.com.soa.repositories;

import br.com.soa.domain.User;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class UserRepository implements PanacheRepositoryBase<User, Integer> {

    public Optional<User> findByUsuario(String username){
       return find("username = ?1", username).firstResultOptional();
    }

}
