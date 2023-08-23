package br.com.soa.repositories;

import br.com.soa.domain.Exercise;
import br.com.soa.domain.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class ExerciseRepository implements PanacheRepositoryBase<Exercise, Integer> {

    public Optional<Exercise> findByExercise(String exerciseName) {
        return find("name = ?1", exerciseName).firstResultOptional();
    }

}
