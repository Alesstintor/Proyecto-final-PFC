package com.ales.fittrackapi.controllers;

import com.ales.fittrackapi.entities.Record;
import com.ales.fittrackapi.services.IRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/record")
public class RecordController {

    @Autowired
    IRecordService recordService;

    @GetMapping("/all")
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

    @PostMapping
    public Record save(@RequestBody Record record) {
        return recordService.save(record);
    }

    @PostMapping("/saveList")
    public List<Record> saveAll(@RequestBody List<Record> records) {
        return recordService.saveAll(records);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        return recordService.deleteById(id);
    }

    @DeleteMapping("/delete/likeExample")
    public String deleteByExample(@RequestBody Record record) {
        return recordService.deleteByExample(record);
    }

    @PutMapping
    public String update(@RequestBody Record record) {
        return recordService.update(record);
    }

}
