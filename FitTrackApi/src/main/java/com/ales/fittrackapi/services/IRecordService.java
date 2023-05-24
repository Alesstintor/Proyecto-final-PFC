package com.ales.fittrackapi.services;

import com.ales.fittrackapi.entities.Record;

import java.util.List;

public interface IRecordService {

    List<Record> findAll();
    Record findById(Long id);
    List<Record> findAllByExample(Record record);
    Record save(Record record);
    List<Record> saveAll(List<Record> records);
    String deleteById(Long id);
    String deleteByExample(Record record);
    Record update(Record record);
}
