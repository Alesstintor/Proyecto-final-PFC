package com.ales.fittrackapi.services.record;

import com.ales.fittrackapi.entities.Record;

import java.util.List;

public interface IRecordService {

    List<Record> findAll();
    Record findById(Long id);
    List<Record> findAllByExample(Record record);
    Record save(Record record, Long id);
    List<Record> saveAll(List<Record> records);
    void deleteById(Long id);
    void deleteByExample(Record record);
    Record update(Record record);
}
