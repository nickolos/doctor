package com.example.doctor.controllers;

import com.example.doctor.entities.InputData;
import com.example.doctor.services.Service;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping(value = "")
public class InputDataController {
    private static final Logger logger = LoggerFactory.getLogger(InputDataController.class);

    private final Gson gson = new Gson();

    @Autowired
    @Qualifier("localInputService")
    private Service inputDataService;

    @CrossOrigin("*")
    @PostMapping(value = "/upload" )
    public ResponseEntity<?> saveData (@RequestParam MultipartFile file) throws IOException {
       logger.info("POST /upload      "+ file.getName());
       inputDataService.save1(file);
       return new ResponseEntity<>("It's OK!", HttpStatus.OK);

    }


}
