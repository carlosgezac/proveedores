package com.proveedores.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public ResponseEntity<?> home() {
        return new ResponseEntity("hello", HttpStatus.OK);
    }
    
    @GetMapping("/restringido")
    public ResponseEntity<?> res() {
        return new ResponseEntity("restringido", HttpStatus.OK);
    }
}
