package com.ales.fittrackapi.controllers.record;

import com.ales.fittrackapi.entities.Record;
import com.ales.fittrackapi.services.record.IRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/records")
public class RecordControllerImpl implements IRecordController {

    @Autowired
    IRecordService recordService;

    @GetMapping
    public List<Record> findAll() {
        return recordService.findAll();
    }

    @GetMapping("/{id}")
    public Record findById(@PathVariable Long id) {
        return recordService.findById(id);
    }

    @GetMapping("/likeExample")
    public List<Record> findAllByExample(@RequestBody Record record) {
        return recordService.findAllByExample(record);
    }

    @GetMapping("/byUser/{id}")
    public List<Record> findAllByUser(@PathVariable Long id) {
        return recordService.findAllByUser(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Record save(@RequestBody Record record) {
        return recordService.save(record);
    }

    @PostMapping("/saveList")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Record> saveAll(@RequestBody List<Record> records) {
        return recordService.saveAll(records);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        recordService.deleteById(id);
    }

    @DeleteMapping("/likeExample")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByExample(@RequestBody Record record) {
        recordService.deleteByExample(record);
    }

    @PutMapping
    public Record update(@RequestBody Record record) {
        return recordService.update(record);
    }

}