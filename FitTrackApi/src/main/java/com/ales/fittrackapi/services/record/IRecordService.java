package com.ales.fittrackapi.services.record;

import com.ales.fittrackapi.entities.Record;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IRecordService {

    List<Record> findAll();
    Record findById(Long id);
    List<Record> findAllByExample(Record record);
    List<Record> findAllByUser(Long id);
    Record save(Record record);
    List<Record> saveAll(List<Record> records);
    void deleteById(Long id);
    void deleteByExample(Record record);
    Record update(Record record);
}
