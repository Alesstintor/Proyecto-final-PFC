package com.ales.fittrackapi.services.record;

import com.ales.fittrackapi.entities.Record;
import com.ales.fittrackapi.entities.User;
import com.ales.fittrackapi.repositories.RecordRepository;
import com.ales.fittrackapi.services.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements IRecordService{

    private final RecordRepository recordRepository;
    private final IUserService userService;

    @Override
    public List<Record> findAll() {
        return recordRepository.findAll();
    }

    @Override
    public Record findById(Long id) {
        return recordRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Record> findAllByExample(Record record) {
        return recordRepository.findAll(Example.of(record));
    }

    @Override
    public List<Record> findAllByUser(Long id) {
        User user = userService.findById(id);
        return recordRepository.findAllByUser(user);
    }

    @Override
    public Record save(Record record) {
        return recordRepository.save(record);
    }

    @Override
    public List<Record> saveAll(List<Record> records) {
        return recordRepository.saveAll(records);
    }

    @Override
    public void deleteById(Long id) {
        recordRepository.delete(findById(id));
    }

    @Override
    public void deleteByExample(Record record) {
        List<Record> recordsToDelete = findAllByExample(record);
        if (recordsToDelete.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        recordRepository.deleteAll(recordsToDelete);
    }

    @Override
    public Record update(Record record) {
        findById(record.getId());
        return recordRepository.save(record);
    }
}
