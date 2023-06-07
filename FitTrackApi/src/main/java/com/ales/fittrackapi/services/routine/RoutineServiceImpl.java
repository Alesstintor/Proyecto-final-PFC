package com.ales.fittrackapi.services.routine;

import com.ales.fittrackapi.entities.Routine;
import com.ales.fittrackapi.repositories.RoutineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoutineServiceImpl implements IRoutineService{

    private final RoutineRepository routineRepository;

    @Override
    public List<Routine> findAll() {
        return routineRepository.findAll();
    }

    @Override
    public Routine findById(Long id) {
        return routineRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Routine> findAllByExample(Routine routine) {
        return routineRepository.findAll(Example.of(routine));
    }

    @Override
    public Routine save(Routine routine) {

        return routineRepository.save(routine);
    }

    @Override
    public List<Routine> saveAll(List<Routine> routines) {
        return routineRepository.saveAll(routines);
    }

    @Override
    public void deleteById(Long id) {
        routineRepository.delete(findById(id));
    }

    @Override
    public void deleteByExample(Routine routine) {
        List<Routine> routinesToDelete = findAllByExample(routine);
        if (routinesToDelete.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        routineRepository.deleteAll(routinesToDelete);
    }

    @Override
    public Routine update(Routine routine) {
        findById(routine.getId());
        return routineRepository.save(routine);
    }
}
