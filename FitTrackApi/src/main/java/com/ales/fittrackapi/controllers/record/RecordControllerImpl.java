package com.ales.fittrackapi.controllers.record;

import com.ales.fittrackapi.entities.Record;
import com.ales.fittrackapi.services.record.IRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/records")
@RequiredArgsConstructor
public class RecordControllerImpl implements IRecordController {


    private final IRecordService recordService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Record> findAll() {
        return recordService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Record findById(@PathVariable Long id) {
        return recordService.findById(id);
    }

    @PostMapping("/likeExample")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Record> findAllByExample(@RequestBody Record record) {
        return recordService.findAllByExample(record);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Record save(@RequestBody Record record, @PathVariable Long id) {
        return recordService.save(record, id);
    }

    @PostMapping("/myUser")
    @ResponseStatus(HttpStatus.CREATED)
    public Record saveForAuthenticatedUser(@RequestBody Record record) {
        return recordService.saveForAuthenticatedUser(record);
    }

    @PostMapping("/saveList")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Record> saveAll(@RequestBody List<Record> records) {
        return recordService.saveAll(records);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteById(@PathVariable Long id) {
        recordService.deleteById(id);
    }

    @DeleteMapping("/likeExample")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteByExample(@RequestBody Record record) {
        recordService.deleteByExample(record);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Record update(@RequestBody Record record) {
        return recordService.update(record);
    }

}
