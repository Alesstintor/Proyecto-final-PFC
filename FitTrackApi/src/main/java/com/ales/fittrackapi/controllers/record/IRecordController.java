package com.ales.fittrackapi.controllers.record;

import com.ales.fittrackapi.entities.Record;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IRecordController {

    List<Record> findAll();

    Record findById(@PathVariable Long id);

    List<Record> findAllByExample(@RequestBody Record record);

    Record save(@RequestBody Record record, Long id);

    List<Record> saveAll(@RequestBody List<Record> records);

    void deleteById(@PathVariable Long id);

    void deleteByExample(@RequestBody Record record);

    Record update(@RequestBody Record record);
}
