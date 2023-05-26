package com.ales.fittrackapi.controllers.record;

import com.ales.fittrackapi.entities.Record;
import com.ales.fittrackapi.services.record.IRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
public class RecordControllerImpl implements IRecordController {


    private final IRecordService recordService;

    @Override
    public List<Record> findAll() {
        return recordService.findAll();
    }

    @Override
    public Record findById(@PathVariable Long id) {
        return recordService.findById(id);
    }

    @Override
    public List<Record> findAllByExample(@RequestBody Record record) {
        return recordService.findAllByExample(record);
    }

    @Override
    public List<Record> findAllByUser(@PathVariable Long id) {
        return recordService.findAllByUser(id);
    }

    @Override
    public Record save(@RequestBody Record record) {
        return recordService.save(record);
    }

    @Override
    public List<Record> saveAll(@RequestBody List<Record> records) {
        return recordService.saveAll(records);
    }

    @Override
    public void deleteById(@PathVariable Long id) {
        recordService.deleteById(id);
    }

    @Override
    public void deleteByExample(@RequestBody Record record) {
        recordService.deleteByExample(record);
    }

    @Override
    public Record update(@RequestBody Record record) {
        return recordService.update(record);
    }

}
