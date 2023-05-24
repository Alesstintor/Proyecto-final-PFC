package com.ales.fittrackapi.controllers;

import com.ales.fittrackapi.entities.Record;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IRecordController {

    @GetMapping("/all")
    List<Record> findAll();

    @GetMapping("/{id}")
    Record findById(@PathVariable Long id);

    @GetMapping("/likeExample")
    List<Record> findAllByExample(@RequestBody Record record);

    @PostMapping
    Record save(@RequestBody Record record);

    @PostMapping("/saveList")
    List<Record> saveAll(@RequestBody List<Record> records);

    @DeleteMapping("/delete/{id}")
    String deleteById(@PathVariable Long id);

    @DeleteMapping("/delete/likeExample")
    String deleteByExample(@RequestBody Record record);

    @PutMapping
    String update(@RequestBody Record record);
}
