package br.com.soa.services;

import br.com.soa.domain.Exercise;
import br.com.soa.dto.ExerciseDTO;
import br.com.soa.repositories.ExerciseRepository;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ExerciseService {

    private static final Logger LOGGER = Logger.getLogger(ExerciseService.class);
    @Inject
    ExerciseRepository repository;

    public List<ExerciseDTO> listAll() {
        return repository.findAll().stream().map((exercise) -> convertToDTO(exercise)).collect(Collectors.toList());
    }

    public ExerciseDTO getExerciseById(Integer exerciseId) {
        var exercise = repository.findByIdOptional(exerciseId).orElseThrow(() -> new NotFoundException("Exercise not found!"));
        return convertToDTO(exercise);
    }

    @Transactional
    public ExerciseDTO saveExercise(ExerciseDTO exerciseDTO) {
        validateExercise(exerciseDTO.getName());

        exerciseDTO.setId(null);
        var exercise = convertToEntity(exerciseDTO);
        repository.persist(exercise);
        return convertToDTO(exercise);
    }

    @Transactional
    public ExerciseDTO updateExercise(ExerciseDTO exerciseDTO, Integer id) {
        validateExercise(exerciseDTO.getName());

        Exercise exercise = repository.findByIdOptional(id).orElseThrow(() -> new NotFoundException("Exercise not found"));
        exercise = updateDataEntity(exerciseDTO, exercise);
        repository.persist(exercise);
        return convertToDTO(exercise);
    }

    public void deleteExercise(Integer exerciseId) {
        var exercise = repository.findByIdOptional(exerciseId).orElseThrow(() -> new NotFoundException("Exercise not found"));
        repository.delete(exercise);
    }

    public void validateExercise(String exerciseName) {
        var result = repository.findByExercise(exerciseName);
        if (result.isPresent()) {
            new Exception("Usuário já cadastrado!");
        }
    }

    public ExerciseDTO convertToDTO(Exercise exercise) {
        ExerciseDTO objDto = new ExerciseDTO();
        objDto.setId(exercise.getId());
        objDto.setName(exercise.getName());
        objDto.setRepetitionSerie(exercise.getRepetitionSerie());
        objDto.setSerie(exercise.getSerie());
        objDto.setTime(exercise.getTime());
        objDto.setWeight(exercise.getWeight());
        return objDto;
    }

    public Exercise convertToEntity(ExerciseDTO objDto) {
        Exercise obj = new Exercise();
        obj.setId(objDto.getId());
        obj.setName(objDto.getName());
        obj.setSerie(objDto.getSerie());
        obj.setRepetitionSerie(objDto.getRepetitionSerie());
        obj.setTime(objDto.getTime());
        obj.setWeight(objDto.getWeight());
        return obj;
    }

    public Exercise updateDataEntity(ExerciseDTO objDto, Exercise exercise) {
        exercise.setName(objDto.getName());
        exercise.setSerie(objDto.getSerie());
        exercise.setRepetitionSerie(objDto.getRepetitionSerie());
        exercise.setTime(objDto.getTime());
        exercise.setWeight(objDto.getWeight());
        return exercise;
    }


}
