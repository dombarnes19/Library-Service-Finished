package com.bloomtech.library.controllers;

import com.bloomtech.library.models.checkableTypes.Checkable;
import com.bloomtech.library.services.CheckableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/checkables")
public class CheckableController {

    @Autowired
    CheckableService checkableService;

    @GetMapping(value = "")
    public ResponseEntity<?> getCheckables() {
        List<Checkable> checkables = checkableService.getAll();
        return new ResponseEntity<>(checkables, HttpStatus.OK);
    }
}
