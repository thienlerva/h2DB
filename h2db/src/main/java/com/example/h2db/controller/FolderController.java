package com.example.h2db.controller;

import com.example.h2db.model.Path;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/folder")
@CrossOrigin("*")
public class FolderController {

    @Value("${path.name}")
    private String name;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping("/")
    public List<String> getAll() {

        System.out.println("defautl url: " + this.name);
        File[] directories = new File(this.name).listFiles(File::isDirectory);
        List<String> directoryList = new ArrayList<>();

        if (directories !=null) {
            List<File> files = Arrays.asList(directories);
            List<Path> fileList = new ArrayList<>();
            for (File f : files) {

                directoryList.add(f.getAbsolutePath());
            }
            System.out.println(files);
            return directoryList;
        }

        return null;
    }

    @GetMapping("/map")
    ResponseEntity<String> getAllOM() throws JsonProcessingException {
        System.out.println("defautl url: " + this.name);
        File[] directories = new File(this.name).listFiles(File::isDirectory);

        if (directories!=null) {
            List<File> files = Arrays.asList(directories);
            List<Path> fileList = new ArrayList<>();
            for (File f : files) {
                Path path = new Path();
                path.setUrl(f.getAbsolutePath());
                fileList.add(path);
            }
            System.out.println("object mapper: " + files);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            return new ResponseEntity<>(objectMapper.writeValueAsString(fileList), headers, HttpStatus.OK);
        }
        return new ResponseEntity<>(objectMapper.writeValueAsString("Unable to process"), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/createDir")
    public ResponseEntity<String> createPath(@RequestBody Path path) {
        System.out.println("from UI: " + path);

        String url;

        if (path.getUrl()==null) {
            url = "/";
        } else {
            url = path.getUrl();
        }

        try {
            File[] directories = new File(url).listFiles(File::isDirectory);
            if (directories != null) {
                this.name = url;
                System.out.println("Updated default path: " + this.name);
                return new ResponseEntity<>("Success", HttpStatus.ACCEPTED);
            }
        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage());
        }

        return new ResponseEntity<>("unable to process", HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/all")
    public List<String> getFolder() {
        System.out.println("Running----------------");
        return Arrays.asList("Shapefile", "GeoTIFF");
    }

    @GetMapping("/one")
    public String getONeFolder() {
        return "Shapefile";
    }

    @PostMapping("/create")
    public ResponseEntity<List<String>> createFolder(@RequestBody List<String> folders) {
        System.out.println("Running program------------");
        if (folders!=null) {
            return new ResponseEntity<>(folders, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Arrays.asList("Unable to create"), HttpStatus.BAD_REQUEST);
        }
    }
}
