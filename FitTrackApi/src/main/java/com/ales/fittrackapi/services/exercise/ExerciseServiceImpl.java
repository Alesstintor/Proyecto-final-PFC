package com.ales.fittrackapi.services.exercise;

import com.ales.fittrackapi.entities.Exercise;
import com.ales.fittrackapi.repositories.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseServiceImpl implements IExerciseService{

    private final ExerciseRepository exerciseRepository;

    @Override
    public List<Exercise> findAll() {
        return exerciseRepository.findAll();
    }

    @Override
    public Exercise findById(Long id) {
        return exerciseRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Exercise> findAllByExample(Exercise exercise) {
        return exerciseRepository.findAll(Example.of(exercise));
    }

    @Override
    public Exercise save(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    @Override
    public List<Exercise> saveAll(List<Exercise> exercises) {
        return exerciseRepository.saveAll(exercises);
    }

    @Override
    public void deleteById(Long id) {
        exerciseRepository.delete(findById(id));
    }

    @Override
    public void deleteByExample(Exercise exercise) {
        List<Exercise> exercisesToDelete = findAllByExample(exercise);
        if (exercisesToDelete.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        exerciseRepository.deleteAll(exercisesToDelete);
    }

    @Override
    public Exercise update(Exercise exercise) {
        findById(exercise.getId());
        return exerciseRepository.save(exercise);
    }
}
