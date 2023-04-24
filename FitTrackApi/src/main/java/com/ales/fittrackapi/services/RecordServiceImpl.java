package com.ales.fittrackapi.services;

import com.ales.fittrackapi.entities.Record;
import com.ales.fittrackapi.repositories.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

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
        return null;
    }

    @Override
    public String deleteByExample(Record record) {
        return null;
    }

    @Override
    public Record update(Record record) {
        return null;
    }
}
