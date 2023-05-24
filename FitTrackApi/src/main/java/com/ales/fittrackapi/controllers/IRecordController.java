package com.ales.fittrackapi.controllers;

import com.ales.fittrackapi.entities.Record;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/records")
public interface IRecordController {

    @GetMapping
    List<Record> findAll();

    @GetMapping("/{id}")
    Record findById(@PathVariable Long id);

    @GetMapping("/likeExample")
    List<Record> findAllByExample(@RequestBody Record record);

    @GetMapping("/byUser/{id}")
    List<Record> findAllByUser(@PathVariable Long id);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Record save(@RequestBody Record record);

    @PostMapping("/saveList")
    @ResponseStatus(HttpStatus.CREATED)
    List<Record> saveAll(@RequestBody List<Record> records);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable Long id);

    @DeleteMapping("/likeExample")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteByExample(@RequestBody Record record);

    @PutMapping
    Record update(@RequestBody Record record);
}
