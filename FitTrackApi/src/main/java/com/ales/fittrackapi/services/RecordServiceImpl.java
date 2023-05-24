package com.ales.fittrackapi.services;

import com.ales.fittrackapi.entities.Record;
import com.ales.fittrackapi.repositories.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RecordServiceImpl implements IRecordService{

    @Autowired
    RecordRepository recordRepository;

    @Override
    public List<Record> findAll() {
        return recordRepository.findAll();
    }

    @Override
    public Record findById(Long id) {
        return recordRepository.findById(id).orElse(null);
    }

    @Override
    public List<Record> findAllByExample(Record record) {
        return recordRepository.findAll(Example.of(record));
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
    public String deleteById(Long id) {
        Record recordToDelete = findById(id);
        if (recordToDelete == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No record found with id: " + id) ;

        recordRepository.deleteById(id);
        return "Record with id: " + id + ", deleted successfully";
    }

    @Override
    public String deleteByExample(Record record) {
        List<Record> recordsToDelete = findAllByExample(record);
        if (recordsToDelete.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No record was found with given example");

        recordRepository.deleteAll(recordsToDelete);
        return recordsToDelete.size() + " records deleted successfully";
    }


    @Override
    public Record update(Record record) {
        Record recordToUpdate = findById(record.getId());
        if (recordToUpdate == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No record found with id " + record.getId());

        return recordRepository.save(record);
    }
}
